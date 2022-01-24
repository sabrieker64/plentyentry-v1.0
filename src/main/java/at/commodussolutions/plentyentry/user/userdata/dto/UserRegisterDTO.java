package at.commodussolutions.plentyentry.user.userdata.dto;

import at.commodussolutions.plentyentry.user.userdata.enums.UserGender;

import java.time.LocalDate;

public class UserRegisterDTO {

    private String firstName;
    private String lastName;
    private Integer svNumber;
    private String email;
    private String password;
    private String city;
    private Integer phoneNumber;
    private String postCode;
    private LocalDate birthday;
    private UserGender userGender;


}
