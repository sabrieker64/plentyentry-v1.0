package at.commodussolutions.plentyentry.user.userdata.dto;

import at.commodussolutions.plentyentry.ordermanagement.event.dto.EventDTO;
import at.commodussolutions.plentyentry.user.coronastate.dto.CoronaStatusDTO;
import at.commodussolutions.plentyentry.user.payment.dto.PaymentMethodDTO;
import at.commodussolutions.plentyentry.user.shoppingcart.dto.ShoppingCartDTO;
import at.commodussolutions.plentyentry.user.userdata.enums.UserGender;
import at.commodussolutions.plentyentry.user.userdata.enums.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Author: @Eker
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String street;
    private String email;
    private String password;
    private String postCode;
    private String city;
    private Integer age;
    private Integer svNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate birthday;
    private UserType userType;
    private UserGender userGender;
    private Set<EventDTO> events;
    private CoronaStatusDTO coronaStatus;
    private List<PaymentMethodDTO> paymentMethod;
    private String jwtToken;
    private ShoppingCartDTO shoppingCartDTO;
    private String companyName;
    private String phoneNumber;
    private String uid;
    private Boolean enabled;

}
