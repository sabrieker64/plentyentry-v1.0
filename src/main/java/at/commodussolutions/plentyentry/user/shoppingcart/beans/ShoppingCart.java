package at.commodussolutions.plentyentry.user.shoppingcart.beans;

import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "shoppingCart")
    private List<Ticket> tickets = new ArrayList<>();

}
