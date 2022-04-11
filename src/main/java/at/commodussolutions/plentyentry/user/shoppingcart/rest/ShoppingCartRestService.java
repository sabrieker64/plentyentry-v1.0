package at.commodussolutions.plentyentry.user.shoppingcart.rest;

import at.commodussolutions.plentyentry.user.shoppingcart.dto.ShoppingCartDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/backend/shoppingcart")
public interface ShoppingCartRestService {

    @GetMapping("/{id}")
    @ResponseBody
    ShoppingCartDTO getShoppingCartById(@PathVariable Long id);

    @PutMapping()
    @ResponseBody
    ShoppingCartDTO updateShoppingCartById(@RequestBody ShoppingCartDTO updatedShoppingCart);

}
