package at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.rest;

import at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.dto.CheckoutSessionDTO;
import at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.dto.StripeCheckoutResultDTO;
import at.commodussolutions.plentyentry.user.userdata.dto.UserDTO;
import com.stripe.model.Account;
import com.stripe.model.AccountLink;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/backend/stripe"})
@CrossOrigin
public interface StripeRestService {


    @PostMapping("/create-checkout-session")
    @ResponseBody
    StripeCheckoutResultDTO createCheckoutSession(@RequestBody CheckoutSessionDTO checkoutSessionDTO);


    @PostMapping("/confirm/{id}")
    @ResponseBody
    ResponseEntity<String> confirmPayment(@PathVariable("id") String id);



    @PostMapping("/cancel/{id}")
    @ResponseBody
    ResponseEntity<String> cancelPayment(@PathVariable("id") String id);


    //Connected Accounts
    @PostMapping("/account/create-account")
    @ResponseBody
    Account createConnectedAccount(@RequestBody UserDTO userDTO);


    @GetMapping("/account?accountId={accountId}")
    @ResponseBody
    AccountLink getAccountLinkWithId(@RequestParam("accountId")String accountId);
}
