package at.commodussolutions.plentyentry.user.shoppingcart.repository;

import at.commodussolutions.plentyentry.user.shoppingcart.beans.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
