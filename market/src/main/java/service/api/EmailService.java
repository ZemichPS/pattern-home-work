package service.api;

import javax.mail.MessagingException;

public interface EmailService {
    void send(String emailTo, String subject, String body) throws MessagingException;
}
