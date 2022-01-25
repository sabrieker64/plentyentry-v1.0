package at.commodussolutions.plentyentry.user.userdata.dto;

import at.commodussolutions.plentyentry.user.userdata.beans.User;
import lombok.Data;

@Data
public class UserAuthResDTO {
    private User user;
    private String jwtToken;
}
