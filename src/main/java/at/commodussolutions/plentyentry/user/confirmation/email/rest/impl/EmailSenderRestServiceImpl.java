package at.commodussolutions.plentyentry.user.confirmation.email.rest.impl;


import at.commodussolutions.plentyentry.user.confirmation.email.EmailService;
import at.commodussolutions.plentyentry.user.confirmation.email.dto.EmailSendDTO;
import at.commodussolutions.plentyentry.user.confirmation.email.dto.EmailSendWithAttachmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/backend/email")
@CrossOrigin
public class EmailSenderRestServiceImpl {

   @Autowired
   private EmailService emailService;

    @Autowired
    private Environment env;

    @PostMapping("/send-email")
    @ResponseBody
    void sendEmail(@RequestBody EmailSendDTO emailSendDTO){

        if(env.acceptsProfiles(Profiles.of("test", "development")) ){
            emailService.send(emailSendDTO.getEmailTo(), emailSendDTO.getEmail());
        }
        emailService.sendEmailFromSES(emailSendDTO.getEmailTo(), emailSendDTO.getEmail(), emailSendDTO.getSubject());

    }

 /*   @PostMapping("/send-email/attachment")
    @ResponseBody
    void sendEmailAttachment(@RequestBody EmailSendWithAttachmentDTO emailSendWithAttachmentDTO) {

        if(env.acceptsProfiles(Profiles.of("test", "development")) ){
            emailService.send(emailSendWithAttachmentDTO.getEmailTo(), emailSendWithAttachmentDTO.getEmail());
        }
        emailService.sendEmailFromSESWithFile(emailSendWithAttachmentDTO.getEmailTo(), emailSendWithAttachmentDTO.getEmail(),
                emailSendWithAttachmentDTO.getSubject(), emailSendWithAttachmentDTO.getFiles());
    }*/
}
