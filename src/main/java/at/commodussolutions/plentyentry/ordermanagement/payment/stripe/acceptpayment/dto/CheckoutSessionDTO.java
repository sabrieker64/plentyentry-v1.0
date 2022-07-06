package at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CheckoutSessionDTO {

    private Long userId;

    private String successUrl;

    private String cancelUrl;

    private BigDecimal fullAmount;
}
