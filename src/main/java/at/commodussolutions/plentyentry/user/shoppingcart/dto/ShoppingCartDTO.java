package at.commodussolutions.plentyentry.user.shoppingcart.dto;

import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketDTO;
import lombok.Data;

import java.util.Set;

@Data
public class ShoppingCartDTO {
    private Long id;
    private Set<TicketDTO> tickets;
}
