package tn.Shamash.Pfe.service;

public interface EmailService {

    void sendSimpleEmail(String to, String subject, String body);
    void send(String to, String mail);
}
