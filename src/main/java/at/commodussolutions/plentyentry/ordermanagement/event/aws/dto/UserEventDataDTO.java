package at.commodussolutions.plentyentry.ordermanagement.event.aws.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEventDataDTO {
    String username;
    String eventName;
}
