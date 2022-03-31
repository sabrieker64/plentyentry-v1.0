package at.commodussolutions.plentyentry.ordermanagement.stripe.acceptpayment.service.impl;

import at.commodussolutions.plentyentry.ordermanagement.event.dto.EventDTO;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeAcceptPaymentService {

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
