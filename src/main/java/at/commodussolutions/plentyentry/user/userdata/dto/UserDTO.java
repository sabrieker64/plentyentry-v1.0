package at.commodussolutions.plentyentry.user.userdata.dto;

import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketDTO;
import at.commodussolutions.plentyentry.user.coronastate.dto.CoronaStatusDTO;
import at.commodussolutions.plentyentry.user.payment.dto.PaymentMethodDTO;
import at.commodussolutions.plentyentry.user.userdata.enums.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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
    private String postCode;
    private String city;
    private Integer age;
    private Integer svNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate birthday;
    private UserType userType;
    private List<TicketDTO> tickets;
    private List<CoronaStatusDTO> coronaStatus;
    private List<PaymentMethodDTO> paymentMethod;


}
