package at.commodussolutions.plentyentry.ordermanagement.qrCode.rest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/backend/qrcode")
public interface QrCodeGeneratorRestService {

    @GetMapping("/{ticketID}")
    @ResponseBody
    String getQRCode(@PathVariable Long ticketID);

    @GetMapping("/scan")
    @ResponseBody
    String useQRCode(Long ticketID);

}
