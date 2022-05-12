package at.commodussolutions.plentyentry.user.shoppingcart.beans;

import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: @Mina
 */

@Entity
@Table(name = "SHOPPING_CART")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TICKETS")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "SHOPPING_CART_ID")
    private Set<Ticket> tickets = new HashSet<>();

}
