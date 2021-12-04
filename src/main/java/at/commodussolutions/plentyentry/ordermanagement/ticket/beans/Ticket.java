package at.commodussolutions.plentyentry.ordermanagement.ticket.beans;

import at.commodussolutions.plentyentry.ordermanagement.event.beans.Event;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TICKET")
@Data
public class Ticket {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "ACTIVITY")
    private Boolean activity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_TICKET_USER_ID"), referencedColumnName = "ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_TICKET_EVENT_ID"), referencedColumnName = "ID")
    private Event event;
}
