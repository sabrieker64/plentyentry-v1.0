package at.commodussolutions.plentyentry.user.shoppingcart.dto;

import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class ShoppingCartTicketDTOPerEvent {
    private List<TicketDTO> ticketDTOS;
    private Integer quantity;
    private BigDecimal amount;
    private String eventName;
    private String eventDescription;
    private LocalDate eventDate;
    private BigDecimal pricePerTicket;
}
