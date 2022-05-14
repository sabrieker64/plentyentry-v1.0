package at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.dto;

import lombok.Data;

@Data
public class CreateTokenDTO {
    private Long cardNumber;
    private Integer expMonth;
    private Integer expYear;
    private Integer cvc;
}
