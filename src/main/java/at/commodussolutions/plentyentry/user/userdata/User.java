package at.commodussolutions.plentyentry.user.userdata;

import at.commodussolutions.plentyentry.ordermanagement.ticket.Ticket;
import at.commodussolutions.plentyentry.user.coronastate.CoronaStatus;
import at.commodussolutions.plentyentry.user.payment.PaymentMethod;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER")
@Data
public class User {

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
    @JoinColumn(referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_TICKET_ID"))
    private List<Ticket> tickets = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_CORONA_STATUS_ID"))
    private List<CoronaStatus> coronaStatus = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_PAYMENT_METHOD_ID"))
    private List<PaymentMethod> paymentMethod = new ArrayList<>();


}
