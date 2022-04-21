package at.commodussolutions.plentyentry.ordermanagement.qrCode.rest.impl;

import at.commodussolutions.plentyentry.ordermanagement.qrCode.QRCodeGenerator;
import at.commodussolutions.plentyentry.ordermanagement.qrCode.rest.QrCodeGeneratorRestService;
import at.commodussolutions.plentyentry.ordermanagement.qrCode.service.QrCodeGeneratorService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;

@RestController
public class QrCodeGeneratorRestServiceImpl implements QrCodeGeneratorRestService {


    @Autowired
    QrCodeGeneratorService qrCodeGeneratorService;

    @Override
    public String getQRCode(Long ticketID){
        return qrCodeGeneratorService.getQRCode(ticketID);
    }

    @Override
    public String useQRCode(Long ticketID) {
        return qrCodeGeneratorService.useQRCode(ticketID);
    }

}
