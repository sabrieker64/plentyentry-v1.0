package at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.rest.impl;

import at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.dto.PaymentIntentDTO;
import at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.service.impl.StripeAcceptPaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@Transactional
@RequestMapping({"/api/backend/stripe"})
public class StripeRestServiceImpl {


    @Value("${stripe.private-key}")
    private String apiKey;


    @Value("${pe.baseHost}")
    private String host;

    @Autowired
    private StripeAcceptPaymentService acceptPayment;


    @PostMapping("/create-payment-intent")
    @ResponseBody
    public ResponseEntity<String> createCheckoutSession(@RequestBody PaymentIntentDTO paymentIntentDTO) throws StripeException {
        Stripe.apiKey = apiKey;
        PaymentIntent paymentIntent = acceptPayment.paymentIntent(paymentIntentDTO);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }

    @PostMapping("/confirm/{id}")
    @ResponseBody
    public ResponseEntity<String> confirmPayment(@PathVariable("id") String id) throws StripeException {
        PaymentIntent paymentIntent = acceptPayment.confirm(id);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }

    @PostMapping("/cancel/{id}")
    @ResponseBody
    public ResponseEntity<String> cancelPayment(@PathVariable("id") String id) throws StripeException {
        PaymentIntent paymentIntent = acceptPayment.cancel(id);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }

}
