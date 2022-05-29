package at.commodussolutions.plentyentry.ordermanagement.ticket.beans;

import at.commodussolutions.plentyentry.ordermanagement.event.beans.Event;
import at.commodussolutions.plentyentry.ordermanagement.ticket.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TICKET")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //todo das nicht mehr befüllen
    // tickets werden jetzt autmotatisch generiert mit der angegebenen anazahl beim event erstellen jedes Ticket ist
    // ein Eintrag so ist es besser das ganze zu verfolgen die Referenz ist die Event ID
    // und wir können so mehrere Ticet Arten für ein Event machen
    //@Column(name = "QUANTITY")
    //private Integer quantity;

    @Column(name = "STATUS")
    private TicketStatus ticketStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_TICKET_EVENT_ID"), referencedColumnName = "ID")
    private Event event;

}
