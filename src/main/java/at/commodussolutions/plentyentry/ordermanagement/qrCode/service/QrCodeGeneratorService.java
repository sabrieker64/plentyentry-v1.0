package at.commodussolutions.plentyentry.ordermanagement.qrCode.service;

import org.springframework.stereotype.Service;

public interface QrCodeGeneratorService {

    public String getQRCode(Long ticketID);

    public String useQRCode(Long ticketID);
}
