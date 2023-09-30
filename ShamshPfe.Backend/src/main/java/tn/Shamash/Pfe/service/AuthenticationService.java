package tn.Shamash.Pfe.service;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import io.jsonwebtoken.io.IOException;
import tn.Shamash.Pfe.Entity.PasswordResetToken;
import tn.Shamash.Pfe.Exception.EmailNotExist;
import tn.Shamash.Pfe.Exception.EmailNotFoundException;
import tn.Shamash.Pfe.Exception.ResetPasswordException;
import tn.Shamash.Pfe.Exception.ResetPasswordTokenException;

public interface AuthenticationService {
    PasswordResetToken generatePasswordResetToken(String email) throws EmailNotFoundException, IOException, TemplateNotFoundException, MalformedTemplateNameException, ParseException, TemplateException, java.io.IOException, EmailNotExist;

    void updatePassword(String token, String newPassword) throws ResetPasswordException, ResetPasswordTokenException;
}
