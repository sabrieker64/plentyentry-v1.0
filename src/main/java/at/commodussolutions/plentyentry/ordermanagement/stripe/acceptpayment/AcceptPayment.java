package at.commodussolutions.plentyentry.ordermanagement.stripe.acceptpayment;

import at.commodussolutions.plentyentry.ordermanagement.event.dto.EventDTO;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;

public class AcceptPayment {

    @Value("${stripe.private-key}")
    private String stripeApiKey;


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

    //This is the call for accepting payment
    // PaymentIntent paymentIntent = PaymentIntent.create(acceptPaymentWithEventDetails())

}
