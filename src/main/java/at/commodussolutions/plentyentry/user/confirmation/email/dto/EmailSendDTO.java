package at.commodussolutions.plentyentry.user.confirmation.email.dto;

import lombok.Data;

@Data
public class EmailSendDTO {

    private String emailTo;

    private String email;

    private String subject;
}
