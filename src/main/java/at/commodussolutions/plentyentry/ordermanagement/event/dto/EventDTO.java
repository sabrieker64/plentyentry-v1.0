package at.commodussolutions.plentyentry.ordermanagement.event.dto;

import at.commodussolutions.plentyentry.user.userdata.dto.UserDTO;
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
    private Long ticketId;
    private String city;
    private String address;
    private List<String> imageUrls;
    private UserDTO userDTO;
}
