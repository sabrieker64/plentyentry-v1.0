package at.commodussolutions.plentyentry.ordermanagement.qrCode.rest.impl;

import at.commodussolutions.plentyentry.ordermanagement.qrCode.rest.QrCodeGeneratorRestService;
import at.commodussolutions.plentyentry.ordermanagement.qrCode.service.QrCodeGeneratorService;
import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class QrCodeGeneratorRestServiceImpl implements QrCodeGeneratorRestService {


    @Autowired
    QrCodeGeneratorService qrCodeGeneratorService;

    @Override
    public byte[] getQRCode(Long ticketID, HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        return qrCodeGeneratorService.getQRCode(ticketID);
    }

    @Autowired
    public String sendQRCodeToUser(List<TicketDTO> ticketDTOList) {
        return qrCodeGeneratorService.sendQRCodeToUser(ticketDTOList);
    }


    @Override
    public String useQRCode(Long ticketID) {
        return qrCodeGeneratorService.useQRCode(ticketID);
    }

}
