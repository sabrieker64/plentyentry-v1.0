package at.commodussolutions.plentyentry.ordermanagement.qrCode.rest.impl;

import at.commodussolutions.plentyentry.ordermanagement.qrCode.rest.QrCodeGeneratorRestService;
import at.commodussolutions.plentyentry.ordermanagement.qrCode.service.QrCodeGeneratorService;
import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketDTO;
import at.commodussolutions.plentyentry.ordermanagement.ticket.mapper.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@RestController
public class QrCodeGeneratorRestServiceImpl implements QrCodeGeneratorRestService {


    @Autowired
    QrCodeGeneratorService qrCodeGeneratorService;
    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public byte[] getQRCode(Long ticketID, HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        return qrCodeGeneratorService.getQRCode(ticketID);
    }

    @Override
    public String sendQRCodeToUser(List<TicketDTO> ticketDTOList) {
        var listOfEntity = new ArrayList<Ticket>();
        ticketMapper.mapToListEntity(ticketDTOList, listOfEntity);
        return  qrCodeGeneratorService.sendQRCodeToUser(listOfEntity);
    }


    @Override
    public String useQRCode(Long ticketID) {
        return qrCodeGeneratorService.useQRCode(ticketID);
    }

}
