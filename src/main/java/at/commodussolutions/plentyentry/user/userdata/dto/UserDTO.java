package at.commodussolutions.plentyentry.user.userdata.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    //TODO: Date Formatter erstellen für String output
    private LocalDate birthday;

    private List<String> tickets = new ArrayList<>();

    private List<String> coronaStatus = new ArrayList<>();

    private List<String> paymentMethod = new ArrayList<>();


}
