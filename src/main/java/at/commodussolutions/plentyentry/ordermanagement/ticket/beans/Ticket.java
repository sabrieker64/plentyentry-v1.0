package at.commodussolutions.plentyentry.ordermanagement.ticket.beans;

import at.commodussolutions.plentyentry.ordermanagement.event.beans.Event;
import at.commodussolutions.plentyentry.ordermanagement.ticket.enums.TicketStatus;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TICKET")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "STATUS")
    private TicketStatus ticketStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_TICKET_EVENT_ID"), referencedColumnName = "ID")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_USER_TICKET_ID"), referencedColumnName = "ID")
    private User user;
}
