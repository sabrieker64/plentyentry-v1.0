package at.commodussolutions.plentyentry.ordermanagement.event.aws.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEventData {
    String username;
    String eventName;
}
