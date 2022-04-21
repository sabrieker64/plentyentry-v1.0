package at.commodussolutions.plentyentry.ordermanagement.qrCode.service.impl;

import at.commodussolutions.plentyentry.ordermanagement.qrCode.QRCodeGenerator;
import at.commodussolutions.plentyentry.ordermanagement.qrCode.service.QrCodeGeneratorService;
import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.ordermanagement.ticket.enums.TicketStatus;
import at.commodussolutions.plentyentry.ordermanagement.ticket.repository.TicketRepository;
import at.commodussolutions.plentyentry.ordermanagement.ticket.service.TicketService;
import com.google.zxing.WriterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Base64;

@Service
@Slf4j
@Transactional
public class QrCodeGeneratorServiceImpl implements QrCodeGeneratorService {

    @Autowired
    TicketRepository ticketRepository;

    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/QRCode.png";


    @Override
    public String getQRCode(Long ticketID) {
        String medium="SETURL/"+ticketID;
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
        String qrcode = Base64.getEncoder().encodeToString(image);

        return qrcode;
    }

    @Override
    public String useQRCode(Long ticketID) {

        //TODO: Check if the logged User is the owner of the Ticket

        Ticket ticket = ticketRepository.findById(ticketID).get();

        if(ticket.getTicketStatus().equals(TicketStatus.NOTUSED) && !ticket.getTicketStatus().equals(TicketStatus.EXPIRED) &&
                !ticket.getTicketStatus().equals(TicketStatus.USED) ) {
            ticket.setTicketStatus(TicketStatus.USED);
            ticketRepository.save(ticket);
            return "Ticket wurde erfolgreich verwendet!";
        }
        return "Ticket kann nicht gescannt werden!";
    }
}
