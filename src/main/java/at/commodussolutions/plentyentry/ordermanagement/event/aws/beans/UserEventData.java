package at.commodussolutions.plentyentry.ordermanagement.event.aws.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEventData {
    String username;
    String eventName;
    List<String> urls;
}
