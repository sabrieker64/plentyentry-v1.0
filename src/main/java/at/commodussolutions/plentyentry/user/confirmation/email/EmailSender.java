package at.commodussolutions.plentyentry.user.confirmation.email;

import javax.mail.MessagingException;
import java.io.File;
import java.util.List;

public interface EmailSender {

    void send(String to, String email);

    void sendEmailFromSES(String to, String email, String subject) throws MessagingException;

    void sendEmailFromSESWithFile(String to, String email, String subject, List<String> qrCodes);
}
