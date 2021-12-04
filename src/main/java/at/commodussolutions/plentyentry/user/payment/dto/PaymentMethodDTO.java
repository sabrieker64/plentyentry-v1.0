package at.commodussolutions.plentyentry.user.payment.dto;

import at.commodussolutions.plentyentry.user.payment.enums.PaymentType;
import at.commodussolutions.plentyentry.user.userdata.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodDTO {
    private Long id;
    private PaymentType paymentType;
    private Integer creditCardNumber;
    private String nameOnCard;
    private Integer cvSecurityCode;
    private Integer iban;
    private UserDTO user;
}
