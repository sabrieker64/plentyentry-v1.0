package at.commodussolutions.plentyentry.ordermanagement.stripe.acceptpayment.rest.impl;

import at.commodussolutions.plentyentry.ordermanagement.stripe.acceptpayment.dto.CreatePayment;
import at.commodussolutions.plentyentry.ordermanagement.stripe.acceptpayment.dto.CreatePaymentResponse;
import at.commodussolutions.plentyentry.ordermanagement.stripe.acceptpayment.service.impl.StripeAcceptPaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    public CreatePaymentResponse createCheckoutSession(@RequestBody CreatePayment createPayment) throws StripeException {
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(15 * 100L)
                        .setCurrency("eur")
                        .setAutomaticPaymentMethods(
                                PaymentIntentCreateParams.AutomaticPaymentMethods
                                        .builder().setEnabled(true).build()).build();

        PaymentIntent intent = PaymentIntent.create(params);
        CreatePaymentResponse createPaymentResponse = new CreatePaymentResponse();
        createPaymentResponse.setClientSecret(intent.getClientSecret());
        return createPaymentResponse;
    }


}
