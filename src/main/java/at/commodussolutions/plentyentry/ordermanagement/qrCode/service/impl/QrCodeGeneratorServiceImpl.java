package at.commodussolutions.plentyentry.ordermanagement.qrCode.service.impl;

import at.commodussolutions.plentyentry.backendConfig.utils.PlentyEntryBackendUtils;
import at.commodussolutions.plentyentry.ordermanagement.qrCode.QRCodeGenerator;
import at.commodussolutions.plentyentry.ordermanagement.qrCode.service.QrCodeGeneratorService;
import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.ordermanagement.ticket.enums.TicketStatus;
import at.commodussolutions.plentyentry.ordermanagement.ticket.repository.TicketRepository;
import at.commodussolutions.plentyentry.ordermanagement.ticket.service.TicketService;
import at.commodussolutions.plentyentry.user.confirmation.email.EmailSender;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
import at.commodussolutions.plentyentry.user.userdata.service.UserService;
import com.google.zxing.WriterException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.ws.rs.BadRequestException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class QrCodeGeneratorServiceImpl implements QrCodeGeneratorService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TicketService ticketService;

    @Autowired
    UserService userService;

    @Autowired
    PlentyEntryBackendUtils plentyEntryBackendUtils;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private Environment environment;

    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/QRCode.png";


    @Override
    public byte[] getQRCode(Long ticketID) {

        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketID);


        if (ticketOptional.isEmpty()) {
            throw new BadRequestException("Ticket existiert nicht");
        }

        Ticket ticket = ticketOptional.get();
        boolean userBoughtTicket = false;

        for (Ticket currentTicket : userService.getUserByJWTToken().getShoppingCart().getTickets()) {
            if (currentTicket.getId().equals(ticket.getId())) {
                userBoughtTicket = true;
                break;
            }
        }

        if(userBoughtTicket) {
            String medium=plentyEntryBackendUtils.getHost()+"api/backend/qrcode/scan/"+ticketID;
            //String github="https://github.com/rahul26021999";

            byte[] image = new byte[0];
            try {

                // Generate and Return Qr Code in Byte Array
                image = QRCodeGenerator.getQRCodeImage(medium,250,250);


                //TO SAVE QR CODE AS FILE -> DO WE NEED THIS??
                //QRCodeGenerator.generateQRCodeImage(github,250,250,QR_CODE_IMAGE_PATH);

            } catch (WriterException | IOException e) {
                e.printStackTrace();
            }

            // Convert Byte Array into Base64 Encode String
            return Base64.getEncoder().encode(image);
            //return Base64.getEncoder().encodeToString(image);
        }
        throw new IllegalArgumentException("Das Ticket wurde nicht von dir gekauft!");
    }

    @Override
    @SneakyThrows
    public String sendQRCodeToUser(List<Ticket> tickets) {

        User user = userService.getUserByJWTToken();

        for (Ticket ticket : tickets) {
            File qrCode =File.createTempFile(ticket.getEvent().getName(), ticket.getId().toString());
            FileOutputStream fileOutputStream = new FileOutputStream(qrCode);
            fileOutputStream.write(this.getQRCode(ticket.getId()));

            String emailText = "Dein QR-Code für das Event " + ticket.getEvent().getName() + "!";
            if (environment.acceptsProfiles(Profiles.of("test", "development"))) {
               emailSender.send(user.getEmail(), buildEmail(user.getLastName(), emailText));
            }else {
                try {
                    emailSender.sendEmailFromSES(user.getEmail(), emailText, "QR-Code " + ticket.getEvent().getName());
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    @Override
    public String useQRCode(Long ticketID) {
        Ticket ticket = ticketService.getTicketById(ticketID);

        boolean entertainerIsAllowedToScan = false;

        for (User entertainer : ticket.getEvent().getEntertainers()) {
            if (entertainer.getId().equals(userService.getUserByJWTToken().getId())) {
                entertainerIsAllowedToScan = true;
                break;
            }
        }

        if (ticket.getTicketStatus().equals(TicketStatus.SELLED) || ticket.getTicketStatus().equals(TicketStatus.RESERVED) && !ticket.getTicketStatus().equals(TicketStatus.USED)
                && entertainerIsAllowedToScan) {
            ticket.setTicketStatus(TicketStatus.USED);
            ticketRepository.save(ticket);
            return "{  \"response\" : \"Ticket wurde erfolgreich verwendet!\" }";
        }

        if (ticket.getTicketStatus().equals(TicketStatus.USED) && entertainerIsAllowedToScan) {
            return "{  \"responseerror\" : \"Ticket wurde schon einmal verwendet!\" }";
        }


        return "{  \"responseerror\" : \"Ticket kann nicht gescannt werden!\" }";

    }

    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
}
