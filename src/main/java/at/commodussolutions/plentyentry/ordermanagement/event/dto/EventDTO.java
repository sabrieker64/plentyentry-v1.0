package at.commodussolutions.plentyentry.ordermanagement.event.dto;

import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
    private Long id;
    private String name;
    private LocalDate date;
    private String description;
    private Double price;
    private Integer ticketCounter;
    private List<TicketDTO> ticket;
}
