package at.commodussolutions.plentyentry.user.payment.paypal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    private String currency;
    private String method;
    private String intent;
    private String description;
    private Double price;
}
