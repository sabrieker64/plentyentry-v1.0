package at.commodussolutions.plentyentry.ordermanagement.qrCode.rest;

import at.commodussolutions.plentyentry.backendConfig.utils.PESecured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import static at.commodussolutions.plentyentry.user.userdata.enums.UserType.*;

@RestController
@RequestMapping("/api/backend/qrcode")
public interface QrCodeGeneratorRestService {

    @GetMapping("/{ticketID}")
    @ResponseBody
    byte[] getQRCode(@PathVariable Long ticketID, HttpServletResponse response);

    @GetMapping("/scan/{ticketID}")
    @ResponseBody
    @PESecured({ADMIN, SUPERADMIN, MAINTAINER})
    String useQRCode(@PathVariable Long ticketID);

}
