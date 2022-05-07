package at.commodussolutions.plentyentry.ordermanagement.qrCode.service.impl;

import at.commodussolutions.plentyentry.backendConfig.utils.PlentyEntryBackendUtils;
import at.commodussolutions.plentyentry.ordermanagement.qrCode.QRCodeGenerator;
import at.commodussolutions.plentyentry.ordermanagement.qrCode.service.QrCodeGeneratorService;
import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.ordermanagement.ticket.enums.TicketStatus;
import at.commodussolutions.plentyentry.ordermanagement.ticket.repository.TicketRepository;
import at.commodussolutions.plentyentry.ordermanagement.ticket.service.TicketService;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
import at.commodussolutions.plentyentry.user.userdata.service.UserService;
import com.google.zxing.WriterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class QrCodeGeneratorServiceImpl implements QrCodeGeneratorService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TicketService ticketService;

    @Autowired
    UserService userService;

    @Autowired
    PlentyEntryBackendUtils plentyEntryBackendUtils;

    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/QRCode.png";


    @Override
    public String getQRCode(Long ticketID) {

        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketID);


        if (ticketOptional.isEmpty()) {
            return "Ticket mit dieser ID existiert nicht";
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

            return Base64.getEncoder().encodeToString(image);
        }
        return "Das Ticket wurde nicht von dir gekauft!";
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

        if (ticket.getTicketStatus().equals(TicketStatus.SELLED) && !ticket.getTicketStatus().equals(TicketStatus.USED)
                && entertainerIsAllowedToScan) {
            ticket.setTicketStatus(TicketStatus.USED);
            ticketRepository.save(ticket);
            return "Ticket wurde erfolgreich verwendet!";
        }
        return "Ticket kann nicht gescannt werden!";
    }
}
