package at.commodussolutions.plentyentry.ordermanagement.payment.stripe.enums;

import lombok.Getter;

@Getter
public enum StripePaymentTypes {
    card,
    giropay,
    sepa_debit
}
