package at.commodussolutions.plentyentry.ordermanagement.payment.stripe.types.dto;

import lombok.Data;

@Data
public class StripeBankTransferDTO {
    private String iban;
}
