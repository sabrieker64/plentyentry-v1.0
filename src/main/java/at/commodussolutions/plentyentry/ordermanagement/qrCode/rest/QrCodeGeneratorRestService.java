package at.commodussolutions.plentyentry.ordermanagement.qrCode.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/backend/qrcode")
public interface QrCodeGeneratorRestService {

    @GetMapping
    public String getQRCode();
}
