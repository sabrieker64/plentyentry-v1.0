package at.commodussolutions.plentyentry.user.shoppingcart.dto;

import lombok.Data;

import java.util.List;

@Data
public class ShoppingCartDTO {
    private Long id;
    private List<ShoppingCartTicketDTOPerEvent> tickets;
}
