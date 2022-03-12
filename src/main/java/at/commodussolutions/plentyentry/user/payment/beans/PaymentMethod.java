package at.commodussolutions.plentyentry.user.payment.beans;


import at.commodussolutions.plentyentry.user.payment.enums.PaymentType;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "PAYMENT_METHOD")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethod {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PAYMENT_TYPE")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(name = "PAYPAL_CLIENT_TOKEN")
    private String paypalClientToken;

    @Column(name = "PAYPAL_CLIENT_TOKEN_EXPIRES_AT")
    private String paypalClientTokenExpiresIn;

    @Column(name = "CREDIT_CARD_NUMBER")
    private Integer creditCardNumber;

    @Column(name = "NAME_ON_CARD")
    private String nameOnCard;

    @Column(name = "CV_SECURITY_CODE")
    private Integer cvSecurityCode;

    @Column(name = "IBAN")
    private Integer iban;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_USER_PAYMENT_METHOD"), referencedColumnName = "ID")
    private User user;

}
