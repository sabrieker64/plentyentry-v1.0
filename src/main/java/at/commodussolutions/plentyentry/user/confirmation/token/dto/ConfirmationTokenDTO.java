package at.commodussolutions.plentyentry.user.confirmation.token.dto;

import at.commodussolutions.plentyentry.user.userdata.beans.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConfirmationTokenDTO {

    private Long id;
    private String token;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime expiresAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime confirmedAt;
    private User user;
}
