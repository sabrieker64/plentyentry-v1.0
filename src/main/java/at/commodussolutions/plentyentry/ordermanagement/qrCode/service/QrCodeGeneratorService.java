package at.commodussolutions.plentyentry.ordermanagement.qrCode.service;

import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketDTO;

import java.util.List;

public interface QrCodeGeneratorService {

     byte[] getQRCode(Long ticketID);

     String sendQRCodeToUser(List<TicketDTO> ticketDTOList);

     String useQRCode(Long ticketID);
}
