package at.commodussolutions.plentyentry.user.confirmation.email;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class EmailService implements EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    private final JavaMailSender mailSender;
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Override
    @Async
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm your Email");
            helper.setFrom("welcome@plenty-entry.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            LOGGER.error("failed to send email", e);
        }

    }

    @Override
    @Async
    public void sendEmailFromSES(String to, String email, String subject) {
        try {
            var mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("welcome@plenty-entry.com");
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error(e.getMessage());
        }

    }

    @Override
    @Async
    @SneakyThrows
    public void sendEmailFromSESWithFile(String to, String subject, String bodyPlainText, String bodyHtml, String contentId, byte[] base64Images) {
        try {
            var mimeMessage = mailSender.createMimeMessage();
            mimeMessage.setSubject(subject);
            mimeMessage.setFrom(new InternetAddress("welcome@plenty-entry.com"));
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent(bodyPlainText, "text/plain; charset=UTF-8");

            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(bodyHtml, "text/html; charset=UTF-8");

            Multipart multipart = new MimeMultipart("alternative");

            multipart.addBodyPart(textPart);
            multipart.addBodyPart(htmlPart);

            mimeMessage.setContent(multipart);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error(e.getMessage());
        }
    }

}
