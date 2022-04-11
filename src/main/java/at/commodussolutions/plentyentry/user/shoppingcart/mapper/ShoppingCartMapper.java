package at.commodussolutions.plentyentry.user.shoppingcart.mapper;

import at.commodussolutions.plentyentry.user.shoppingcart.beans.ShoppingCart;
import at.commodussolutions.plentyentry.user.shoppingcart.dto.ShoppingCartDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ShoppingCartMapper {

    ShoppingCartDTO mapToDTO(ShoppingCart shoppingCart);

    ShoppingCart mapToEntity(ShoppingCartDTO shoppingCartDTO, @MappingTarget ShoppingCart shoppingCart);
}
