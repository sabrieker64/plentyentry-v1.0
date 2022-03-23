package at.commodussolutions.plentyentry.user.payment.stripe.config;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {

    @Value("${stripe.private-key}")
    private String privateKey;

    private void testStripe() throws StripeException {
        RequestOptions requestOptions = RequestOptions.builder()
                .setApiKey(privateKey)
                .build();
        Charge charge = Charge.retrieve("ch_3Kfr2MDAXhrIxCz90sw2RQvc", requestOptions);
    }
}