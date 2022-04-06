package at.commodussolutions.plentyentry.user.payment.paypal.rest.impl;

import at.commodussolutions.plentyentry.backendConfig.utils.PlentyEntryBackendUtils;
import at.commodussolutions.plentyentry.user.payment.paypal.dto.Order;
import at.commodussolutions.plentyentry.user.payment.paypal.rest.PayPalRestService;
import at.commodussolutions.plentyentry.user.payment.paypal.service.PayPalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class PayPalRestServiceImpl implements PayPalRestService {

    private final String successURL = "/pay/successful";
    private final String cancelURL = "/pay/cancel";

    @Autowired
    private PayPalService payPalService;

    @Autowired
    private PlentyEntryBackendUtils plentyEntryBackendUtils;

    @Override
    public String paymentPayPal(@ModelAttribute("order") Order order) throws PayPalRESTException {
        try {
            Payment payment = payPalService.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(), order.getIntent(), order.getDescription(),
                    plentyEntryBackendUtils.getHost() + cancelURL, plentyEntryBackendUtils.getHost() + successURL);
            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    return "redirect: " + links.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping(value = cancelURL)
    @ResponseBody
    public String paymentPayPalCancel() {
        return "cancel";
    }

    @GetMapping(value = successURL)
    public String paymentPayPalSuccess(@RequestParam("paymentId") String paymentId, @RequestParam("payerId") String payerId) {
        try {
            Payment payment = payPalService.executePayment(paymentId, payerId);
            log.info(payment.toString());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return "redirect:/";
    }
}
