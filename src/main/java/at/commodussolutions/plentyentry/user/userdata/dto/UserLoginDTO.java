package at.commodussolutions.plentyentry.user.userdata.dto;

import at.commodussolutions.plentyentry.user.userdata.beans.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
   private User user;
   private String jwtToken;
}
