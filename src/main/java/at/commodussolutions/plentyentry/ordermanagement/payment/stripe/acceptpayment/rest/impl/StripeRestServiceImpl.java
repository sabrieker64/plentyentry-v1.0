package at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.rest.impl;

import at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.dto.CheckoutSessionDTO;
import at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.dto.CreateTokenDTO;
import at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.dto.StripeCheckoutResultDTO;
import at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.service.impl.StripeAcceptPaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.BadRequestException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping({"/api/backend/stripe"})
public class StripeRestServiceImpl {


    @Value("${stripe.private-key}")
    private String apiKey;


    @Value("${pe.baseHost}")
    private String host;

    @Autowired
    private StripeAcceptPaymentService acceptPayment;


    @PostMapping("/create-checkout-session")
    @ResponseBody
    public StripeCheckoutResultDTO createCheckoutSession(@RequestBody CheckoutSessionDTO checkoutSessionDTO) throws StripeException {
        Stripe.apiKey = apiKey;
        var createCheckoutSession = acceptPayment.createCheckoutSession(checkoutSessionDTO);
        var stripeCheckoutUrl = new StripeCheckoutResultDTO();
        stripeCheckoutUrl.setUrlToStripe(createCheckoutSession.getUrl());
        return stripeCheckoutUrl;
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


    @PostMapping("/create-token")
    @ResponseBody
    public Token createTokenForStripePayment(@RequestBody CreateTokenDTO createTokenDTO) throws StripeException {
        Stripe.apiKey = apiKey;
        Map<String, Object> card = new HashMap<>();
        card.put("number", createTokenDTO.getCardNumber());
        card.put("exp_month", createTokenDTO.getExpMonth());
        card.put("exp_year", createTokenDTO.getExpYear());
        card.put("cvc", createTokenDTO.getCvc());
        Map<String, Object> params = new HashMap<>();
        params.put("card", card);
        var response = acceptPayment.createToken(params);
        if (Objects.isNull(response.getId())) {
            throw new BadRequestException("Die Bezahlung hat fehlgeschlagen");
        }
        return response;
    }

}
