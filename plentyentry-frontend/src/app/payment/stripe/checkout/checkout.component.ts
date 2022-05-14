import {Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../../environments/environment";
import {loadStripe, StripeCardElementOptions, StripeElementsOptions} from "@stripe/stripe-js";
import {PaymentService} from "../../service/payment.service";
import {StripeCardNumberComponent, StripeElementsService} from "ngx-stripe";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

declare var Stripe: any;

@Component({
  selector: 'app-stripe',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss']
})
export class CheckoutComponent implements OnInit {
  @ViewChild(StripeCardNumberComponent) card: StripeCardNumberComponent;
  stripePromise = loadStripe(environment.stripe);
  elementsOptions: StripeElementsOptions = {
    locale: 'de'
  };
  cardOptions: StripeCardElementOptions = {
    style: {
      base: {
        iconColor: '#666EE8',
        color: '#7F73D4FF',
        padding: '50px',
        fontWeight: '400',
        fontFamily: '"Helvetica Neue", Helvetica, sans-serif',
        fontSize: '18px',
        '::placeholder': {
          color: '#CFD7E0'
        }
      }
    }
  };

  public stripeForm: FormGroup;

  //todo hier muss ich das objekt im unserm fall das event das sich der kunde angeschaut und auf bezahlen geklickt hat
  //todo achtung ganz wichtig es kann auch aus dem warenkorb kommen mit einer listen von events oder

  constructor(private http: HttpClient, private service: PaymentService, private stripeService: StripeElementsService, private fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.stripeForm = this.fb.group({
      name: ['', [Validators.required]]
    })

  }


  public createCharge() {
  }

  makePayment() {
    console.log('test payment');
    //todo make rest call to stripe api endpoint
    //todo after creating the payment object, confirm or cancel the paymnet

  }

  getToken() {

  }

  createToken() {

  }
}
