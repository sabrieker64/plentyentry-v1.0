package at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.dto;

import at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.enums.Currency;
import at.commodussolutions.plentyentry.ordermanagement.payment.stripe.enums.StripePaymentTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentIntentDTO implements Serializable {
    @NotNull
    @JsonProperty("currency")
    private Currency currency;
    @NotNull
    @JsonProperty("amount")
    private BigDecimal amount;
    @NotNull
    @JsonProperty("eventId")
    private Long eventId;
    @Nullable
    @JsonProperty("description")
    private String description;
    @Enumerated(EnumType.STRING)
    @JsonProperty("paymentType")
    private StripePaymentTypes paymentType;
}
