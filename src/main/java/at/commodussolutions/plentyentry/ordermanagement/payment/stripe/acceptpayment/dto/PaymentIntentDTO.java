package at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.dto;

import at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.enums.Currency;
import at.commodussolutions.plentyentry.ordermanagement.payment.stripe.types.StripePaymentTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentIntentDTO implements Serializable {
    private Currency currency;
    private BigDecimal amount;
    private Long eventId;
    private String description;
    //für den anfang werden wir nur kredit/debit karten akzeptieren über stripe
    @Enumerated(EnumType.STRING)
    private StripePaymentTypes paymentType;
    private String orderId;
}
