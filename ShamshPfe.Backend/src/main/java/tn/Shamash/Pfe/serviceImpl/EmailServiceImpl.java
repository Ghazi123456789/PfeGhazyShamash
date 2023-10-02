package tn.Shamash.Pfe.serviceImpl;

import lombok.AllArgsConstructor;
import tn.Shamash.Pfe.service.EmailService;

import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@AllArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {
	  private JavaMailSender javaMailSender;

        @Override
        @Async
        public void send(String to, String email) {
            try {
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper helper =
                        new MimeMessageHelper(mimeMessage, "utf-8");
                helper.setText(email, true);
                helper.setTo(to);
                helper.setSubject("Confirm your email");
                helper.setFrom("gestionrh.dpc@gmail.com");
                javaMailSender.send(mimeMessage);
            } catch (MessagingException e) {
                throw new IllegalStateException("failed to send email");
            }
        }
        @Override
    public void sendSimpleEmail(String toEmail, String subject, String body)
    {
        	System.out.println("eeeeeeeeeeeeee");
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("fromemail@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(body, true);
            javaMailSender.send(message);
            System.out.println("Mail Sent...");
        } catch (MessagingException e) {
            throw new MailParseException(e);
        }
    }
        
        public void sendEmail(String to, String subject, String body) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("ShamashPFE@gmail.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            System.out.println("Mail Sent...");
            javaMailSender.send(message);
            System.out.println("Mail Sent...");
        }
}
