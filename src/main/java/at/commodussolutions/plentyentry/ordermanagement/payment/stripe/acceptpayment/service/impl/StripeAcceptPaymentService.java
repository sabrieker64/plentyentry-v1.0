package at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.service.impl;

import at.commodussolutions.plentyentry.ordermanagement.event.dto.EventDTO;
import at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.dto.PaymentIntentDTO;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentIntentDTO.getAmount());
        params.put("description", paymentIntentDTO.getDescription());
        params.put("currency", paymentIntentDTO.getCurrency());

        ArrayList<Object> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");
        params.put("payment_method_types", paymentMethodTypes);
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
