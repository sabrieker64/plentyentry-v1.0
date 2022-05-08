package at.commodussolutions.plentyentry.ordermanagement.ticket.repository;

import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    //List<Ticket> findAllByUser(User user);

    //List<Ticket> findAllByShoppingCartId(ShoppingCart shoppingCart);

}