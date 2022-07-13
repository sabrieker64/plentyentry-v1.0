package at.commodussolutions.plentyentry.ordermanagement.qrCode.rest;

import at.commodussolutions.plentyentry.backendConfig.utils.PESecured;
import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static at.commodussolutions.plentyentry.user.userdata.enums.UserType.*;

@RestController
@RequestMapping("/api/backend/qrcode")
@CrossOrigin
public interface QrCodeGeneratorRestService {

    @GetMapping("/{ticketID}")
    @ResponseBody
    byte[] getQRCode(@PathVariable Long ticketID, HttpServletResponse response);

    @PostMapping("/sendMail")
    @ResponseBody
    String sendQRCodeToUser(@RequestBody List<TicketDTO> ticketDTOList);

    @GetMapping("/scan/{ticketID}")
    @ResponseBody
    @PESecured({ADMIN, SUPERADMIN, MAINTAINER})
    String useQRCode(@PathVariable Long ticketID);

}
