package at.commodussolutions.plentyentry.user.userdata.beans;

import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.user.coronastate.beans.CoronaStatus;
import at.commodussolutions.plentyentry.user.payment.beans.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: @Eker
 */

@Entity
@Table(name = "USER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "STREET")
    private String street;

    @Column(name = "POST_CODE")
    private String postCode;

    @Column(name = "CITY")
    private String city;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "SV_NUMBER")
    private Integer svNumber;

    @Column(name = "BIRTHDAY")
    private LocalDate birthday;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_USER_TICKET_ID"))
    private List<Ticket> tickets = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_USER_CORONA_STAT_ID"))
    private List<CoronaStatus> coronaStatus = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_USER_PAYMENT_ID"))
    private List<PaymentMethod> paymentMethod = new ArrayList<>();


}
