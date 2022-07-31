import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {LoginRegisterService} from "../service/login-register.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {CheckoutSessionDTO, PaymentIntentDTO, StripeCheckoutResultDTO, UserAuthReqDTO, UserDTO} from "../../definitions/objects";
import {HttpErrorResponse} from "@angular/common/http";
import {ErrorService} from "../../../library/error-handling/error.service";
import {EventService} from "../../events/service/event.service";
import {toNumbers} from "@angular/compiler-cli/src/version_helpers";
import {environment} from "../../../environments/environment";
import {ShoppingcartService} from "../shoppingcart/service/shoppingcart.service";
import {throwError} from "rxjs";

@Component({
  selector: 'app-login-register',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent implements OnInit {
  userAuthReqDTO: UserAuthReqDTO = <UserAuthReqDTO>{};
  loginFormGroup: FormGroup;
  userDTO : UserDTO = <UserDTO> {};
  fieldTextType: any;
  paymenIntent: PaymentIntentDTO = <PaymentIntentDTO>{};
  checkoutDTO: CheckoutSessionDTO = <CheckoutSessionDTO>{};
  resultOfStripe: StripeCheckoutResultDTO = <StripeCheckoutResultDTO>{};
  fullPrice: number;
  eventId: number;
  quantity: number;
  showProgressSpinner: boolean = false;

  constructor(private router: Router, private loginRegisterService: LoginRegisterService,
              private fb: FormBuilder, private errorHandling: ErrorService, private eventService: EventService,
              private shoppincartService: ShoppingcartService) {
  }

  ngOnInit(): void {
    this.eventId  = parseInt(localStorage.getItem('eventId'));
    this.quantity = parseInt( localStorage.getItem('quantity'));
    this.loadEventWithEventId(parseInt(localStorage.getItem('eventId')), parseInt(localStorage.getItem('quantity')));
    this.loginFormGroup = this.fb.group({
      "email": new FormControl('', [Validators.required, Validators.pattern(this.loginRegisterService.regex.email)]),
      "password": new FormControl('', [Validators.required, Validators.pattern(this.loginRegisterService.regex.passwort)])
    });

  }

  loadEventWithEventId(id: number, quantity: number){
    this.eventService.getEventById(id)
      .toPromise().then(event => {
      this.fullPrice = event.price;
      this.fullPrice = this.fullPrice * quantity;
      console.log(this.fullPrice);
    });
  }

  authenticate() {
    this.showProgressSpinner = true;
    this.loginRegisterService.authenticateUser(this.userAuthReqDTO).toPromise().then((userDTO) => {
      localStorage.setItem('token', userDTO.jwtToken);
      if(localStorage.getItem('eventId') && localStorage.getItem('quantity')){
        if(localStorage.getItem('directBuy') === 'true'){
          this.directPayment();
        }else{
          this.addTicketToShoppingCartAsGuest();
        }
      }else{
        this.router.navigateByUrl('/event/overview').then((res) => {
          window.location.reload();
        });
      }
      this.showProgressSpinner = false;
    }).catch((error: HttpErrorResponse) => {
      this.errorHandling.openInformation('Passwort oder Email ist falsch bitte überprüfen Sie ihre Eingabe');
      this.loginRegisterService.getUserByEmail(this.userAuthReqDTO.email).toPromise().then(user => {
        if(!user.enabled){
          this.errorHandling.openInformation('Sie haben noch ihre Email Adresse nicht bestätigt, bitte bestätigen Sie es bevor Sie sich anmelden');
        }
      }).catch((error: HttpErrorResponse) => {
        this.errorHandling.openInformation('Passwort oder Email ist falsch bitte überprüfen Sie ihre Eingabe');
      });
    });
  }

  changePasswordType() {
    this.fieldTextType = !this.fieldTextType;
  }

  justAddTheTicketsToShoppingCartForPayment() {
    this.eventService.selectTicketsAndAddToCustomerShoppingCart(this.eventId, this.quantity).toPromise().then(data => {
      console.log(data);
    });
  }

  directPayment() {
    this.justAddTheTicketsToShoppingCartForPayment();
    this.paymenIntent.amount = this.fullPrice;
    this.paymenIntent.currency = "EUR";
    this.checkoutDTO.fullAmount = this.fullPrice;
    this.checkoutDTO.cancelUrl = environment.frontendBaseUrl + '/payment/cancel';
    this.checkoutDTO.successUrl = environment.frontendBaseUrl + '/payment/success';
    this.shoppincartService.makePaymentWithCheckoutSession(this.checkoutDTO)
      .subscribe(result => {
        this.resultOfStripe = result;
        window.location.href = result.urlToStripe;
      });
    localStorage.removeItem('directBuy');
    localStorage.removeItem('quantity');
    localStorage.removeItem('eventId');
  }

  addTicketToShoppingCartAsGuest() {

    this.eventService.selectTicketsAndAddToCustomerShoppingCart(this.eventId, this.quantity).toPromise().then(data => {
      console.log(data);
      localStorage.removeItem('eventId');
      localStorage.removeItem('quantity');
    });
    this.router.navigateByUrl('/shoppingcart/list').then(res =>{
      location.reload();
    });
  }
}
