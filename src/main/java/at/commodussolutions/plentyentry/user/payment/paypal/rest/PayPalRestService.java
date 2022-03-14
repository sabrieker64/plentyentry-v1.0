package at.commodussolutions.plentyentry.user.payment.paypal.rest;

import at.commodussolutions.plentyentry.user.payment.paypal.dto.Order;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = {"/api/backend/payment"})
public interface PayPalRestService {

    @GetMapping("/pay")
    @ResponseBody
    String paymentPayPal(@ModelAttribute("order") Order order) throws PayPalRESTException;

}
