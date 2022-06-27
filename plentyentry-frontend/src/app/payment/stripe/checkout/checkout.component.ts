import {Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../../environments/environment";
import {PaymentService} from "../../service/payment.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import {PaymentIntentDTO, ShoppingCartDTO, ShoppingCartTicketDTOPerEvent} from "../../../definitions/objects";
import {UserDetailService} from "../../../user/service/user-detail.service";
import {StripeService} from "../stripe.service";
import {StripeCardComponent, StripeElementsService} from "ngx-stripe";
import {TicketService} from "../../../ticket/service/ticket.service";


@Component({
  selector: 'app-stripe',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss']
})
export class CheckoutComponent implements OnInit {
  @ViewChild(StripeCardComponent) card: StripeCardComponent;
  cardElement: any;
  stripe: any;
  paymentIntent: PaymentIntentDTO = <PaymentIntentDTO>{};
  events: ShoppingCartTicketDTOPerEvent;
  fullAmount: number = 0;
  public stripeForm: FormGroup;
  eventId: string = "Keine Ahnung";
  ownerOfShoppingCart: string = " Noch Keiner ";
  quantity: string;
  elements: any;
  clientSecret: string;
  shoppingCart: ShoppingCartDTO;

  constructor(private http: HttpClient, private service: PaymentService,
              private stripeService: StripeElementsService, private fb: FormBuilder, private serviceStripe: StripeService,
              private route: ActivatedRoute, private userService: UserDetailService, private ticketService: TicketService) {
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(data => {
      this.clientSecret = data['client'];
      console.log(this.clientSecret);
    });
    this.stripe = Stripe(environment.stripe);
    this.elements = this.stripe.elements();
    var styleCard = {
      'style': {
        'base': {
          'fontFamily': 'Arial, sans-serif',
          'fontSize': '15px',
          'color': '#7f73d4',
        },
        'Invalid': {'color': 'red',},
      }
    }
    // Remove Zip-code in card UI component
    this.cardElement = this.elements.create('card', {style: styleCard});
    this.cardElement.mount('#card-element');
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
    //todo warum zu fick funkt des nid amk
    console.log(fullAmount);
    console.log(this.clientSecret);
    var elements = this.elements;
    this.stripe.confirmPayment({
      elements,
      confirmParams: {
        return_url: '/events/overview'
      },
    }).then(function (data: any) {
      if (data.error) {
        console.log(data.error);
      }
      if (data.paymentIntent) {
        console.log(data.paymentIntent);
      }
    });
  }
}
