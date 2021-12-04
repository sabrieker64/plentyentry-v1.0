package at.commodussolutions.plentyentry.ordermanagement.ticket.dto;

import at.commodussolutions.plentyentry.ordermanagement.event.dto.EventDTO;
import at.commodussolutions.plentyentry.user.userdata.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    private Long id;
    private Integer quantity;
    private Boolean activity;
    private UserDTO user;
    private EventDTO event;
}
