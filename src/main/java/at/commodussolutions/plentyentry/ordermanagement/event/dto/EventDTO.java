package at.commodussolutions.plentyentry.ordermanagement.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private Long ticketId;
}
