package at.commodussolutions.plentyentry.ordermanagement.event.dto;

import at.commodussolutions.plentyentry.user.userdata.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
    private Long id;
    private String name;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String description;
    private BigDecimal price;
    private Long ticketCounter;
    private Long ticketId;
    private String city;
    private String address;
    private List<String> eventImageUrls;
    private Set<UserDTO> entertainers;

}
