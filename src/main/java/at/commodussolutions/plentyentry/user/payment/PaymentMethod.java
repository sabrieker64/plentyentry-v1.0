package at.commodussolutions.plentyentry.user.payment;


import at.commodussolutions.plentyentry.user.payment.enums.PaymentType;
import at.commodussolutions.plentyentry.user.userdata.User;
import lombok.Data;

import javax.persistence.*;

@Table(name = "PAYMENT_METHOD")
@Entity
@Data
public class PaymentMethod {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "PAYMENT_TYPE")
    private PaymentType paymentType;

    @Column(name = "CREDIT_CARD_NUMBER")
    private Integer creditCardNumber;

    @Column(name = "NAME_ON_CARD")
    private String nameOnCard;

    @Column(name = "CV_SECURITY_CODE")
    private Integer cvSecurityCode;

    @Column(name = "IBAN")
    private Integer iban;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_PAYMENT_METHOD_USER_ID"))
    private User user;


}
