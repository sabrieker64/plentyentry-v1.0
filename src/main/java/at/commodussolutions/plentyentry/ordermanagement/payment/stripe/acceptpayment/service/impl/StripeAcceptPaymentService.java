package at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.service.impl;

import at.commodussolutions.plentyentry.ordermanagement.event.dto.EventDTO;
import at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.dto.PaymentIntentDTO;
import at.commodussolutions.plentyentry.ordermanagement.payment.stripe.types.StripePaymentTypes;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        List<Object> paymentMethodThatCanBeAccepted = Arrays.stream(StripePaymentTypes.values()).collect(Collectors.toList());
        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentIntentDTO.getAmount());
        params.put("description", paymentIntentDTO.getDescription());
        params.put("currency", paymentIntentDTO.getCurrency().getValue());
        params.put("payment_method_types", paymentMethodThatCanBeAccepted);
        return PaymentIntent.create(params);
    }


    public PaymentIntent confirm(String id) throws StripeException {
        Stripe.apiKey = secretKey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
        Map<String, Object> params = new HashMap<>();
        params.put("payment_method", "pm_card_visa");
        paymentIntent.confirm(params);
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

}
