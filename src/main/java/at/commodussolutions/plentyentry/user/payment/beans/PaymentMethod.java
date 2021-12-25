package at.commodussolutions.plentyentry.user.payment.beans;


import at.commodussolutions.plentyentry.user.payment.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "PAYMENT_METHOD")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethod {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "PAYMENT_TYPE")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(name = "CREDIT_CARD_NUMBER")
    private Integer creditCardNumber;

    @Column(name = "NAME_ON_CARD")
    private String nameOnCard;

    @Column(name = "CV_SECURITY_CODE")
    private Integer cvSecurityCode;

    @Column(name = "IBAN")
    private Integer iban;

}
