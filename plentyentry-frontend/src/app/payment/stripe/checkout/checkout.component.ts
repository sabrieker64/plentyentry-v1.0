import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../../environments/environment";
import {loadStripe} from "@stripe/stripe-js";
import {PaymentService} from "../../service/payment.service";
import {StripeService} from "../stripe.service";

declare var Stripe: any;

@Component({
  selector: 'app-stripe',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss']
})
export class CheckoutComponent implements OnInit {
  stripePromise = loadStripe(environment.stripe);
  public stripe: any = null;
  public card: any = null;
  public elements: any = null;
  public cardError: null = null;
  public chargeError: any = null;
  public charge: any = null;
  elementsOptions: any;
  cardOptions: any;

  //todo hier muss ich das objekt im unserm fall das event das sich der kunde angeschaut und auf bezahlen geklickt hat
  //todo achtung ganz wichtig es kann auch aus dem warenkorb kommen mit einer listen von events oder

  constructor(private http: HttpClient, private service: PaymentService, private stripeService: StripeService) {
  }

  ngOnInit(): void {

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
