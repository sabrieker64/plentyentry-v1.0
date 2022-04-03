package at.commodussolutions.plentyentry.ordermanagement.event.aws.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AWSEventImagesUploadDTO {
    String username;
    String eventName;
    List<String> urls;
}
