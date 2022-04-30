package at.commodussolutions.plentyentry.ordermanagement.payment.stripe.types.dto;

import lombok.Data;

@Data
public class StripeCardPaymentDTO {
    private String expireMonth;
    private String expireYear;
    private String cardNumber;
    private String cvc;
}
