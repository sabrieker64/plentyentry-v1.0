package at.commodussolutions.plentyentry.ordermanagement.event;

import at.commodussolutions.plentyentry.ordermanagement.ticket.Ticket;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EVENT")
@Data
public class Event {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "TICKET_COUNTER")
    private Integer ticketCounter;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_EVENT_TICKET_ID"))
    private List<Ticket> ticket = new ArrayList<>();
}