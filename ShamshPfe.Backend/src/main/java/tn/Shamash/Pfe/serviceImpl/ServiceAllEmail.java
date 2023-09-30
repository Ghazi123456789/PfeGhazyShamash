package tn.Shamash.Pfe.serviceImpl;

import com.sun.mail.smtp.SMTPTransport;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import io.jsonwebtoken.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import tn.Shamash.Pfe.Entity.User;
import tn.Shamash.Pfe.Repository.UserRepository;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


@Service
public class ServiceAllEmail {

    @Autowired
    Configuration configuration;

    @Autowired
    UserRepository userRepository;

    private Session getEmailSession() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.port", 465);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.starttls.required", true);
        return Session.getInstance(properties, null);
    }

    String getResetEmailContent(String token) throws IOException, TemplateException, TemplateNotFoundException, MalformedTemplateNameException, ParseException, java.io.IOException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap();
        model.put("token", token);
        configuration.getTemplate("ResetEmail.ftlh").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }
    //Reset Password Email -----------------------------------------------------------------------------------
    private MimeMessage createresetPasswordMail(String token, String email) throws MessagingException, IOException, TemplateNotFoundException, MalformedTemplateNameException, ParseException, TemplateException, java.io.IOException {
        User user = userRepository.findByEmail(email).orElse(null);
        MimeMessage message = new MimeMessage(getEmailSession());
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(new InternetAddress("grhdpc@gmail.com"));
        helper.setTo(email);
        helper.setCc("achref.benhadjyahia@esprit.tn");
        helper.setSubject("gestion Des ressources Humaines - Password Reset");
        String emailContent = getResetEmailContent(token);
        helper.setText(emailContent, true);
        helper.setSentDate(new Date());
        return message;
    }
    public void sendNewResetPasswordMail(String token, String email) throws MessagingException, IOException, TemplateNotFoundException, MalformedTemplateNameException, ParseException, TemplateException, java.io.IOException {
        Message message = createresetPasswordMail(token, email);
        SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport("smtps");
        smtpTransport.connect("smtp.gmail.com", "grhdpc@gmail.com", "Achref1997*");
        smtpTransport.sendMessage(message, message.getAllRecipients());
        smtpTransport.close();
    }
}
