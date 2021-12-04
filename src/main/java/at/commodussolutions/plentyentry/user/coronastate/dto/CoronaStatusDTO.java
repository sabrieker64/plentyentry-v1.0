package at.commodussolutions.plentyentry.user.coronastate.dto;

import at.commodussolutions.plentyentry.user.userdata.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoronaStatusDTO {
    private Long id;
    private Boolean pcrTest;
    private Boolean antigenTest;
    private Boolean firstVaccine;
    private Boolean secondVaccine;
    private Boolean thirdVaccine;
    private Boolean quarantine;
    private UserDTO user;
}
