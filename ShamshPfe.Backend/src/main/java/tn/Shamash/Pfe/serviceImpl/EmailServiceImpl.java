package tn.Shamash.Pfe.serviceImpl;

import lombok.AllArgsConstructor;
import tn.Shamash.Pfe.service.EmailService;

import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@AllArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javamailsender;

        @Override
        @Async
        public void send(String to, String email) {
            try {
                MimeMessage mimeMessage = javamailsender.createMimeMessage();
                MimeMessageHelper helper =
                        new MimeMessageHelper(mimeMessage, "utf-8");
                helper.setText(email, true);
                helper.setTo(to);
                helper.setSubject("Confirm your email");
                helper.setFrom("gestionrh.dpc@gmail.com");
                javamailsender.send(mimeMessage);
            } catch (MessagingException e) {
                throw new IllegalStateException("failed to send email");
            }
        }
        @Override
    public void sendSimpleEmail(String toEmail, String subject, String body)
    {
        MimeMessage message = javamailsender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("fromemail@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(body, true);
            javamailsender.send(message);
            System.out.println("Mail Sent...");
        } catch (MessagingException e) {
            throw new MailParseException(e);
        }
    }
}
