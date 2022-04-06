package at.commodussolutions.plentyentry.user.shoppingcart.beans;

import at.commodussolutions.plentyentry.ordermanagement.event.beans.Event;
import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.user.userdata.beans.User;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Author: @Mina
 */

@Entity
@Table(name = "SHOPPINGCART")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "shoppingCart")
    private User user;

    @OneToMany(mappedBy="shoppingCart")
    private Set<Ticket> tickets;

}
