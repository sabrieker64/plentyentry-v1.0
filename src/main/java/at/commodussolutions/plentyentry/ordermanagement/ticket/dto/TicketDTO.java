package at.commodussolutions.plentyentry.ordermanagement.ticket.dto;

import at.commodussolutions.plentyentry.ordermanagement.event.dto.EventDTO;
import at.commodussolutions.plentyentry.ordermanagement.ticket.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    private Long id;
    private Integer quantity;
    private TicketStatus ticketStatus;
    private String qrCode;
    private EventDTO event;
}
