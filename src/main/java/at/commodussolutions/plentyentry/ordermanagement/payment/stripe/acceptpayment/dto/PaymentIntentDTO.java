package at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.dto;

import at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.enums.Currency;
import at.commodussolutions.plentyentry.user.payment.enums.PaymentType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Data
public class PaymentIntentDTO {
    private Currency currency;
    private String description;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
}
