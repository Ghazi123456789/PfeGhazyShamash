package tn.Shamash.Pfe.Controllers;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import tn.Shamash.Pfe.Dto.Response.JwtResponse;
import tn.Shamash.Pfe.Dto.Response.MessageResponse;
import tn.Shamash.Pfe.Dto.Response.TokenRefreshResponse;
import tn.Shamash.Pfe.Entity.RefreshToken;
import tn.Shamash.Pfe.Entity.Role;
import tn.Shamash.Pfe.Entity.User;
import tn.Shamash.Pfe.Entity.confirmationMailTokenEntity;
import tn.Shamash.Pfe.Enum.ERole;
import tn.Shamash.Pfe.Exception.*;
import tn.Shamash.Pfe.JWT.JwtUtils;
import tn.Shamash.Pfe.Repository.RoleRipository;
import tn.Shamash.Pfe.Repository.UserRepository;
import tn.Shamash.Pfe.Request.LoginRequest;
import tn.Shamash.Pfe.Request.SignupRequest;
import tn.Shamash.Pfe.Request.TokenRefreshRequest;
import tn.Shamash.Pfe.service.AuthenticationService;
import tn.Shamash.Pfe.service.EmailService;
import tn.Shamash.Pfe.serviceImpl.ConfirmationMailTokenService;
import tn.Shamash.Pfe.serviceImpl.ConfirmationTokenService;
import tn.Shamash.Pfe.serviceImpl.RefreshTokenService;
import tn.Shamash.Pfe.serviceImpl.UserDetailsImpl;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
@AllArgsConstructor
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private AuthenticationService authenticationService;
    final AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRipository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    RefreshTokenService refreshTokenService;

    private final ConfirmationTokenService confirmationTokenService;
    private final EmailService emailService;
    private final ConfirmationMailTokenService confirmationEmailToken;


    
    @CrossOrigin(origins = "*")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser( @RequestBody LoginRequest loginRequest) throws AccountLockedException, EntityNotFound {
        User user = userRepository.findByUsername(loginRequest.getUsername()).orElse(null);
        if (user != null) {
        if (!user.isLocked()){

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Your account has been locked. Please contact the administration!");
        }
            if (!user.isEnabled()){

                throw new AccountLockedException("merci de verifier votre compte");
            }
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(),
                userDetails.getUsername(), userDetails.getEmail(), roles));}
    
    
    
    @CrossOrigin(origins = "*")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setDescription(signUpRequest.getDescription());
        user.setCin(signUpRequest.getCIN());
        user.setTel(signUpRequest.getPhone());
        user.setEnabled(false);
        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_EMPLOYE)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MANAGER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_EMPLOYE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        try{
            user.setRoles(roles);
            userRepository.save(user);
            String token = UUID.randomUUID().toString();
            confirmationMailTokenEntity confirmationMailTokenEntity =
                    new confirmationMailTokenEntity(token, LocalDateTime.now(),LocalDateTime.now().plusHours(12),user);
            confirmationTokenService.saveConfirmationToken(confirmationMailTokenEntity);
            String link = "http://localhost:8020/api/auth/confirm?token=" + token;
            emailService.sendSimpleEmail(
                    user.getEmail(),
                    "Please confirm your account",
                    confirmationEmailToken.buildEmail(user.getName(), link));
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage()) ;
        }

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }
    @PostMapping("/reset-password")
    public ResponseEntity<?> generatePasswordResetToken(@RequestParam String email) throws EmailNotExist, io.jsonwebtoken.io.IOException, TemplateNotFoundException, MalformedTemplateNameException, ParseException, TemplateException, IOException, EmailNotFoundException {
        return new ResponseEntity<>(authenticationService.generatePasswordResetToken(email), HttpStatus.OK);
    }

    @PostMapping("/reset-password/new")
    public ResponseEntity<?> updatePassword(@RequestParam String token, @RequestBody String newPassword) throws ResetPasswordException, ResetPasswordTokenException {
        authenticationService.updatePassword(token, newPassword);
        return new ResponseEntity<>("Your password has been successfully updated !", HttpStatus.OK);
    }
    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return confirmationEmailToken.confirmToken(token);
    }

/*
    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getId();
        refreshTokenService.deleteByUserId(userId);
        return ResponseEntity.ok(new MessageResponse("Log out successful!"));
    }*/}