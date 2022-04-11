package at.commodussolutions.plentyentry.user.shoppingcart.dto;

import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketDTO;
import at.commodussolutions.plentyentry.user.userdata.dto.UserDTO;
import lombok.Data;

import java.util.Set;

@Data
public class ShoppingCartDTO {
    private Long id;
    private UserDTO user;
    private Set<TicketDTO> tickets;
}
