import {Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../../environments/environment";
import {loadStripe, StripeCardElementOptions, StripeElementsOptions} from "@stripe/stripe-js";
import {PaymentService} from "../../service/payment.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import {Observable} from "rxjs";
import {PaymentIntentDTO, ShoppingCartDTO, ShoppingCartTicketDTOPerEvent} from "../../../definitions/objects";
import {UserDetailService} from "../../../user/service/user-detail.service";
import {StripeService} from "../stripe.service";
import {StripeCardComponent, StripeElementsService} from "ngx-stripe";


@Component({
  selector: 'app-stripe',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss']
})
export class CheckoutComponent implements OnInit {
  @ViewChild(StripeCardComponent) card: StripeCardComponent;
  stripePromise = loadStripe(environment.stripe);
  stripe: any;
  paymentIntent: PaymentIntentDTO = <PaymentIntentDTO>{};
  events: ShoppingCartTicketDTOPerEvent;
  fullAmount: number = 0;
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
        fontSize: '19px',
        '::placeholder': {
          color: '#7e73d4'
        }
      },
      invalid: {
        iconColor: '#F14520',
        color: '#FB2C00'
      }
    }
  };
  public stripeForm: FormGroup;
  eventId: string = "Keine Ahnung";
  ownerOfShoppingCart: string = " Noch Keiner ";
  quantity: string;
  calculatedAmount: number;
  shoppingCartObserver: Observable<ShoppingCartDTO>;
  shoppingCart: ShoppingCartDTO;


  //todo hier muss ich das objekt im unserm fall das event das sich der kunde angeschaut und auf bezahlen geklickt hat
  //todo achtung ganz wichtig es kann auch aus dem warenkorb kommen mit einer listen von events oder

  constructor(private http: HttpClient, private service: PaymentService,
              private stripeService: StripeElementsService, private fb: FormBuilder, private serviceStripe: StripeService,
              private route: ActivatedRoute, private userService: UserDetailService) {
  }

  ngOnInit(): void {

    this.stripeForm = this.fb.group({
      name: ['', [Validators.required]]
    });
    this.loadShoppingCart();
    this.loadOwnerOfShoppingCart();
  }

  private loadShoppingCart() {
    this.serviceStripe.getShoppingcart().toPromise().then(data => {
      this.shoppingCart = data;
      data.tickets.forEach(ticket => {
        //hier wird die summe des warenkorbs berechnet
        this.fullAmount = this.fullAmount + ticket.amount;
        this.fullAmount = Math.round(this.fullAmount * 100 + Number.EPSILON) / 100;
        this.fullAmount.toFixed(2);
      })
    });
  }

  private loadOwnerOfShoppingCart() {
    this.userService.getCurrentUser().toPromise().then(data => {
      this.ownerOfShoppingCart = data.firstName;
    });
  }

  makePayment(fullAmount: number) {
    console.log(fullAmount);
    const paymentIntent = this.paymentIntent
    paymentIntent.amount = fullAmount;
    this.serviceStripe.makePaymentIntent(this.paymentIntent).toPromise().then(data => {
      if (data.id != null) {
        this.serviceStripe.confirmPayment(data.id).toPromise().then(res => {
          console.log(res);
        })
      }
      console.log(data);
    })

  }


}
