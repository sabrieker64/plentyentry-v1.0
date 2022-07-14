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
import com.amazonaws.services.dynamodbv2.xspec.S;
import com.google.zxing.WriterException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.ws.rs.BadRequestException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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


    private String emailText;


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
        var qrcodes = new ArrayList<String>();
        String eventName = null;

        for (Ticket ticket : tickets) {

            String base64Code = Base64.getEncoder().encodeToString(this.getQRCode(ticket.getId()));
            qrcodes.add(base64Code);
            eventName = ticket.getEvent().getName();
            emailText  = "Dein QR-Code für das Event " + ticket.getEvent().getName() + "!";

       /*     ByteArrayInputStream bis = new ByteArrayInputStream(this.getQRCode(ticket.getId()));
            BufferedImage bImage2 = ImageIO.read(bis);
            ImageIO.write(bImage2, "png", new File(ticket.getEvent().getName()+".png") );
            */
            emailSender.sendEmailFromSESWithFile(user.getEmail(), buildEmailForQrCodes(base64Code, emailText), "QR-Code " + eventName, qrcodes);

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

    private String buildEmailForQrCodes(String qrCodes, String text) {
        return "<h2>\""+ text +"\"</h2>" +
                "<img width=\"400\" height=\"400\" "
                + "alt=\"View of the object.\" src=\"data:image/jpeg;base64,"
                + qrCodes + "\">";
    }
}
