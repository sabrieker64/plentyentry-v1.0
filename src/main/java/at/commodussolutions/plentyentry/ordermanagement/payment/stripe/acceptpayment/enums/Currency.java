package at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.enums;

import lombok.Getter;

@Getter
public enum Currency {
    EUR("eur");

    private final String value;

    Currency(String value) {
        this.value = value;
    }
}
