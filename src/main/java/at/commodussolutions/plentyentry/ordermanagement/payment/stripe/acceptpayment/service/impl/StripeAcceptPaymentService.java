package at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.service.impl;

import at.commodussolutions.plentyentry.ordermanagement.event.dto.EventDTO;
import at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.dto.PaymentIntentDTO;
import at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.enums.Currency;
import at.commodussolutions.plentyentry.ordermanagement.payment.stripe.types.StripePaymentTypes;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Token;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@Transactional
public class StripeAcceptPaymentService {

    @Value("${stripe.private-key}")
    String secretKey;

    @Value("${stripe.public-key}")
    String publicKey;


    public PaymentIntent paymentIntent(PaymentIntentDTO paymentIntentDTO) throws StripeException {
        Stripe.apiKey = secretKey;
        paymentIntentDTO.setCurrency(Currency.EUR);
        paymentIntentDTO.setPaymentType(StripePaymentTypes.card);
        //Hier wird das Objekt für die PaymentIntent aufgebaut
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(paymentIntentDTO.getAmount().multiply(BigDecimal.valueOf(100L)).longValue())
                        .setDescription(paymentIntentDTO.getDescription())
                        .addPaymentMethodType(paymentIntentDTO.getPaymentType().toString())
                        .putMetadata("order_id", paymentIntentDTO.getOrderId())
                        .setCurrency(paymentIntentDTO.getCurrency().getValue())
                        .build();
        return PaymentIntent.create(params);
    }


    public PaymentIntent confirm(String id) throws StripeException {
        Stripe.apiKey = secretKey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
        Map<String, Object> params = new HashMap<>();
        params.put("payment_method", "pm_card_visa");
        paymentIntent.confirm(params);
        //der ticket status wird im frontend gesetzt damit es bereit ist für den scan!
        return paymentIntent;
    }

    public PaymentIntent cancel(String id) throws StripeException {
        Stripe.apiKey = secretKey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
        paymentIntent.cancel();
        return paymentIntent;
    }

    public PaymentIntentCreateParams acceptPaymentWithEventDetails(EventDTO eventDTO) {
        return PaymentIntentCreateParams.builder()
                .setAmount(eventDTO.getPrice().longValue())
                .setCurrency("eur")
                .setAutomaticPaymentMethods(
                        PaymentIntentCreateParams.AutomaticPaymentMethods
                                .builder()
                                .setEnabled(true)
                                .build()).build();
    }


    public static int calculateOrderAmount(Object[] items) {
        // todo calc the invoiceAmount
        return 1400;
    }

    public Token createToken(Map<String, Object> params) throws StripeException {
        Token token = new Token();
        try {
            token = Token.create(params);
        } catch (StripeException e) {
            log.error(e.getMessage());
            {
            }
        }
        return token;
    }
}
