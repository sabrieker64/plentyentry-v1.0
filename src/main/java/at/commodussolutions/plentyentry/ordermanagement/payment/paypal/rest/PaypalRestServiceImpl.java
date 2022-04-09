package at.commodussolutions.plentyentry.ordermanagement.payment.paypal.rest;

import at.commodussolutions.plentyentry.backendConfig.utils.PlentyEntryBackendUtils;
import at.commodussolutions.plentyentry.ordermanagement.payment.paypal.dto.Order;
import at.commodussolutions.plentyentry.ordermanagement.payment.paypal.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/backend/paypal"})
@Slf4j
public class PaypalRestServiceImpl {

    @Autowired
    private PaypalService paypalService;

    @Autowired
    private PlentyEntryBackendUtils plentyEntryBackendUtils;

    public static final String SUCCESS_URL = "/pay/success";
    public static final String CANCEL_URL = "/pay/cancel";

    @PostMapping("/pay")
    @ResponseBody
    public String payment(@ModelAttribute("order") Order order) throws PayPalRESTException {
        try {
            Payment payment = paypalService.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
                    order.getIntent(), order.getDescirption(),
                    plentyEntryBackendUtils.getHost() + CANCEL_URL, plentyEntryBackendUtils.getHost() + SUCCESS_URL);
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return "redirect:" + link.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }


    @GetMapping(value = CANCEL_URL)
    @ResponseBody
    public String cancelPay() {
        return "cancel";
    }


    @GetMapping(value = SUCCESS_URL)
    @ResponseBody
    public String succesPay(@RequestParam("paymentId") String paymentId, @RequestParam("payerId") String payerId) {
        try {

            Payment payment = paypalService.executePayment(paymentId, payerId);
            log.info(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/";
    }


}
