package at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class CreatePayment {

    @SerializedName("items")
    Object[] items;

}
