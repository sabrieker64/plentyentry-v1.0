package at.commodussolutions.plentyentry.user.userdata.beans;

import at.commodussolutions.plentyentry.ordermanagement.event.beans.Event;
import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.user.coronastate.beans.CoronaStatus;
import at.commodussolutions.plentyentry.user.payment.beans.PaymentMethod;
import at.commodussolutions.plentyentry.user.userdata.enums.UserType;
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

    @Column(name = "USER_TYPE")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_USER_MAINTAINED_EVENTS"))
    private List<Event> maintainedEvents;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_USER_TICKET_ID"))
    private List<Ticket> tickets = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_USER_CORONA_STAT_ID"))
    private CoronaStatus coronaStatus;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_USER_PAYMENT_ID"))
    private List<PaymentMethod> paymentMethod = new ArrayList<>();


}
