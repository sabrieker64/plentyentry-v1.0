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
import java.time.LocalDate;
import java.util.Set;

/**
 * Author: @Eker
 */

@Entity
@Table(name = "USER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME", columnDefinition = ("VARCHAR2(50 CHAR)"))
    private String firstName;

    @Column(name = "LAST_NAME", columnDefinition = ("VARCHAR2(50 CHAR)"))
    private String lastName;

    @Column(name = "EMAIL", columnDefinition = ("VARCHAR2(50 CHAR)"))
    private String email;

    @Column(name = "STREET", columnDefinition = ("VARCHAR2(100 CHAR)"))
    private String street;

    @Column(name = "POST_CODE", columnDefinition = ("VARCHAR2(10 CHAR)"))
    private String postCode;

    @Column(name = "CITY", columnDefinition = ("VARCHAR2(50 CHAR)"))
    private String city;

    @Column(name = "AGE", columnDefinition = ("NUMBER(11, 0)"))
    private Integer age;

    @Column(name = "SV_NUMBER")
    private Integer svNumber;

    @Column(name = "BIRTHDAY")
    private LocalDate birthday;

    @Column(name = "USER_TYPE")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Event> maintainedEvents;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Ticket> tickets;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_USER_CORONA_STAT_ID"))
    private CoronaStatus coronaStatus;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PaymentMethod> paymentMethod;


}
