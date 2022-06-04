package at.commodussolutions.plentyentry.user.confirmation.email;

import javax.mail.MessagingException;

public interface EmailSender {

    void send(String to, String email);

    void sendEmailFromSES(String to, String email) throws MessagingException;
}
