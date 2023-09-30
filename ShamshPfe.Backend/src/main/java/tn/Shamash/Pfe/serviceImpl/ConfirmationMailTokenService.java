package tn.Shamash.Pfe.serviceImpl;

import lombok.AllArgsConstructor;
import tn.Shamash.Pfe.Entity.confirmationMailTokenEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class ConfirmationMailTokenService {
    @Autowired
    private final UserDetailsServiceImpl UserService;
    private final ConfirmationTokenService confirmationTokenService;
    private final TemplateEngine templateEngine;


    @Transactional
    public String confirmToken(String token) {
        confirmationMailTokenEntity confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        UserService.enableUser(
                confirmationToken.getUser().getEmail());
        return "confirmed";
    }
    public String buildEmail(String name, String link) {
        Context context = new Context();
        context.setVariable("link", link);
        name="Hello "+name;
        context.setVariable("name", name);
        String html = templateEngine.process("email-verification-template.html", context);

        return  html;
    }
}
