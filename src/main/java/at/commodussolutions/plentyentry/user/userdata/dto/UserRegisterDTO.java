package at.commodussolutions.plentyentry.user.userdata.dto;

import at.commodussolutions.plentyentry.user.shoppingcart.dto.ShoppingCartDTO;
import at.commodussolutions.plentyentry.user.userdata.enums.UserGender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRegisterDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String city;
    private String postCode;
    private String street;
    private LocalDate birthday;
    private UserGender userGender;
    private ShoppingCartDTO shoppingCartDTO;

}
