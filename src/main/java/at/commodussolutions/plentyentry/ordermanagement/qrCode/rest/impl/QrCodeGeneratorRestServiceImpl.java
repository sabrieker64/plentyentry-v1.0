package at.commodussolutions.plentyentry.ordermanagement.qrCode.rest.impl;

import at.commodussolutions.plentyentry.ordermanagement.qrCode.QRCodeGenerator;
import at.commodussolutions.plentyentry.ordermanagement.qrCode.rest.QrCodeGeneratorRestService;
import com.google.zxing.WriterException;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;

@RestController
public class QrCodeGeneratorRestServiceImpl implements QrCodeGeneratorRestService {
    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/QRCode.png";

    @Override
    public String getQRCode(){
        String medium="https://rahul26021999.medium.com/";
        String github="https://github.com/rahul26021999";

        byte[] image = new byte[0];
        try {

            // Generate and Return Qr Code in Byte Array
            image = QRCodeGenerator.getQRCodeImage(medium,250,250);

            // Generate and Save Qr Code Image in static/image folder
            QRCodeGenerator.generateQRCodeImage(github,250,250,QR_CODE_IMAGE_PATH);

        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        // Convert Byte Array into Base64 Encode String
        String qrcode = Base64.getEncoder().encodeToString(image);

        return qrcode;
    }

}
