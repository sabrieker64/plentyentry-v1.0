package at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PricePerEventDTO {

    private Long eventId;

    private BigDecimal price;
}
