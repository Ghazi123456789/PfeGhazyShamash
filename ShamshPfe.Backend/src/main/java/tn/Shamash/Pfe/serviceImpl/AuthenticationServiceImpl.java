package tn.Shamash.Pfe.serviceImpl;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tn.Shamash.Pfe.Entity.PasswordResetToken;
import tn.Shamash.Pfe.Entity.User;
import tn.Shamash.Pfe.Exception.EmailNotExist;
import tn.Shamash.Pfe.Exception.ResetPasswordException;
import tn.Shamash.Pfe.Exception.ResetPasswordTokenException;
import tn.Shamash.Pfe.Repository.PasswordResetTokenRepository;
import tn.Shamash.Pfe.Repository.UserRepository;
import tn.Shamash.Pfe.service.AuthenticationService;

import javax.mail.MessagingException;
import java.time.LocalDateTime;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    ServiceAllEmail emailService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
    @Override
    public PasswordResetToken generatePasswordResetToken(String email) throws EmailNotExist, io.jsonwebtoken.io.IOException, TemplateNotFoundException, MalformedTemplateNameException, ParseException, TemplateException, java.io.IOException {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user!=null) {
            PasswordResetToken token = new PasswordResetToken();
            LocalDateTime nowDate = LocalDateTime.now();
            token.setCreateDate(nowDate);
            String tokenValue = RandomString.make(45);
            token.setUserId(user.getId());
            token.setToken(tokenValue);
            token.setExprirationDate(nowDate.plusMinutes(15));

            passwordResetTokenRepository.save(token);
            try {
                emailService.sendNewResetPasswordMail(token.getToken(), email);
            } catch (MessagingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return token;
        }
        else {
            throw new EmailNotExist("Could not find any user related to the email : " + email);

        }

    }
    @Override
    public void updatePassword(String token, String newPassword) throws ResetPasswordException, ResetPasswordTokenException{
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token);
        User u = userRepository.findById(resetToken.getUserId()).orElse(null);
        if (token != null ) {

            if ( resetToken.getExprirationDate().isAfter(LocalDateTime.now() ) ) {


                String encodedPassword = passwordEncoder.encode(newPassword);
                u.setPassword(encodedPassword);
                userRepository.save(u);
                passwordResetTokenRepository.delete(resetToken);
            }

            else {
                throw new ResetPasswordTokenException("Reset Password Request has expired ! !");
            }

        }
        else {
            throw new ResetPasswordException("Error processing Reset Password Request !");
        }

    }
}
