package at.commodussolutions.plentyentry.ordermanagement.qrCode.service;

public interface QrCodeGeneratorService {

     String getQRCode(Long ticketID);

     String useQRCode(Long ticketID);
}
