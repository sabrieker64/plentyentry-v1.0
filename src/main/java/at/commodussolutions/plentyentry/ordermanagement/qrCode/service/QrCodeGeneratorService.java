package at.commodussolutions.plentyentry.ordermanagement.qrCode.service;

public interface QrCodeGeneratorService {

     byte[] getQRCode(Long ticketID);

     String useQRCode(Long ticketID);
}
