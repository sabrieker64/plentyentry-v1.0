package at.commodussolutions.plentyentry.user.confirmation.email;

public interface EmailSender {

    void send( String to, String email);
}
