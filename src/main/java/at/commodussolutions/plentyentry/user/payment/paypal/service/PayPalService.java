package at.commodussolutions.plentyentry.user.payment.paypal.service;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

public interface PayPalService {

    Payment createPayment(Double total, String currency, String method,
                          String intent, String description, String cancelUrl,
                          String succesUrl) throws PayPalRESTException;

    Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;

}
