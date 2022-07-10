package at.commodussolutions.plentyentry.user.confirmation.email.dto;

import lombok.Data;

import java.io.File;
import java.util.List;

@Data
public class EmailSendWithAttachmentDTO {

    private String emailTo;

    private String email;

    private String subject;

    private List<File> files;
}
