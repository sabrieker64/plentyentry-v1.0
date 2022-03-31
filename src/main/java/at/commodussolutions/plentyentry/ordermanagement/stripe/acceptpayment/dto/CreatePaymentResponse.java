package at.commodussolutions.plentyentry.ordermanagement.stripe.acceptpayment.dto;

import lombok.Data;

@Data
public class CreatePaymentResponse {
    private String clientSecret;
}
