package at.commodussolutions.plentyentry.ordermanagement.qrCode.rest.impl;

import at.commodussolutions.plentyentry.ordermanagement.qrCode.rest.QrCodeGeneratorRestService;
import at.commodussolutions.plentyentry.ordermanagement.qrCode.service.QrCodeGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class QrCodeGeneratorRestServiceImpl implements QrCodeGeneratorRestService {


    @Autowired
    QrCodeGeneratorService qrCodeGeneratorService;

    @Override
    public byte[] getQRCode(Long ticketID, HttpServletResponse response){
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        return qrCodeGeneratorService.getQRCode(ticketID);
    }

    @Override
    public String useQRCode(Long ticketID) {
        return qrCodeGeneratorService.useQRCode(ticketID);
    }

}
