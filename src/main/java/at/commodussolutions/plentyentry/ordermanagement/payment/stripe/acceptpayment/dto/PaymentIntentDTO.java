package at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.dto;

import at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.enums.Currency;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentIntentDTO {

    private Currency currency;
    private String description;
    private BigDecimal amount;
}
