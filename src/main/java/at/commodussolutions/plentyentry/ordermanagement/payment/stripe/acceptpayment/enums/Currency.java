package at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.enums;

import lombok.Getter;

@Getter
public enum Currency {
    //Im moment ist nur EUR als Währung für uns relevant, deshalb brauchen wir keine exchange daten im moment
    EUR("eur");
    private final String value;
    Currency(String value) {
        this.value = value;
    }
}
