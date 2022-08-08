package at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.rest.impl;

import at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.dto.CheckoutSessionDTO;
import at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.dto.CreateTokenDTO;
import at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.dto.StripeCheckoutResultDTO;
import at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.rest.StripeRestService;
import at.commodussolutions.plentyentry.ordermanagement.payment.stripe.acceptpayment.service.impl.StripeAcceptPaymentService;
import at.commodussolutions.plentyentry.user.userdata.dto.UserDTO;
import at.commodussolutions.plentyentry.user.userdata.enums.UserType;
import at.commodussolutions.plentyentry.user.userdata.mapper.UserMapper;
import at.commodussolutions.plentyentry.user.userdata.service.UserService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.AccountLink;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Token;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAllowedException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Transactional
public class StripeRestServiceImpl implements StripeRestService {


    @Value("${stripe.private-key}")
    private String apiKey;


    @Value("${pe.baseHost}")
    private String host;

    @Autowired
    private StripeAcceptPaymentService acceptPayment;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;


    @Override
    @SneakyThrows
    public StripeCheckoutResultDTO createCheckoutSession(@RequestBody CheckoutSessionDTO checkoutSessionDTO)  {
        Stripe.apiKey = apiKey;
        var createCheckoutSession = acceptPayment.createCheckoutSession(checkoutSessionDTO);
        var stripeCheckoutUrl = new StripeCheckoutResultDTO();
        stripeCheckoutUrl.setUrlToStripe(createCheckoutSession.getUrl());
        return stripeCheckoutUrl;
    }




    @Override
    @SneakyThrows
    public ResponseEntity<String> confirmPayment(@PathVariable("id") String id) {
        PaymentIntent paymentIntent = acceptPayment.confirm(id);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }

    @Override
    @SneakyThrows
    public ResponseEntity<String> cancelPayment(@PathVariable("id") String id) {
        PaymentIntent paymentIntent = acceptPayment.cancel(id);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }

    @Override
    @SneakyThrows
    public Account createConnectedAccount(UserDTO userDTO) {
        if(userDTO.getUserType().equals(UserType.MAINTAINER)|| userDTO.getUserType().equals(UserType.ADMIN)){
            Map<String, Object> cardPayments = new HashMap<>();
            cardPayments.put("requested", true);
            Map<String, Object> transfer = new HashMap<>();
            transfer.put("requested", true);

            Map<String, Object> capabilities = new HashMap<>();
            capabilities.put("card_payments", cardPayments);
            capabilities.put("transfer", transfer);
            //eventuell land auch mit dem user speichern für konten ausserhalb österreichs
            Map<String, Object> params = new HashMap<>();
            params.put("type", "custom");
            params.put("country", "AT");
            params.put("email", userDTO.getEmail());
            params.put("capabilities", capabilities);
            var account =   Account.create(params);
            //If the request was succesfully the account id will be added to the user and with this id he can do anything we provide for his
            //connected stripe account
            userDTO.setConnectedAccountID(account.getId());
            var userEntity = userService.getUserById(userDTO.getId());
            userService.updateUser(userMapper.mapToEntity(userDTO, userEntity));
            return account;
        }else {
         throw new NotAllowedException("Sie sind kein Entertainer oder Admin sie dürfen kein Connected Account erstellen");
        }
    }

    @Override
    @SneakyThrows
    public AccountLink getAccountLinkWithId(String accountId) {
        Map<String, Object> params = new HashMap<>();
        params.put("account", accountId);
        params.put("refresh_url", "hier kommt die base url von uns");
        params.put("return_url", "hier auch");
        params.put("type", "account_onboarding");
        AccountLink accountLink = AccountLink.create(params);
        if(accountLink.getUrl() != null){
            return accountLink;
        }else {
            throw new BadRequestException("Es ist ein Fehler aufgetreten, probieren Sie es nochmal oder kontaktieren Sie den Support");
        }
    }

}
