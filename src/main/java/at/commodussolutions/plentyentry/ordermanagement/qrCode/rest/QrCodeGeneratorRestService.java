package at.commodussolutions.plentyentry.ordermanagement.qrCode.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/backend/qrcode")
public interface QrCodeGeneratorRestService {

    @GetMapping("/{ticketID}")
    @ResponseBody
    byte[] getQRCode(@PathVariable Long ticketID, HttpServletResponse response);

    @GetMapping("/scan/{ticketID}")
    @ResponseBody
    String useQRCode(@PathVariable Long ticketID);

}
