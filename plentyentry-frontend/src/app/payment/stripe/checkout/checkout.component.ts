import {Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../../environments/environment";
import {PaymentService} from "../../service/payment.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import {PaymentIntentDTO, ShoppingCartDTO, ShoppingCartTicketDTOPerEvent, UserDTO} from "../../../definitions/objects";
import {UserDetailService} from "../../../user/service/user-detail.service";
import {StripeService} from "../stripe.service";
import {StripeCardComponent, StripeElementsService} from "ngx-stripe";
import {TicketService} from "../../../ticket/service/ticket.service";
import {loadStripe, Stripe} from "@stripe/stripe-js";


declare var makePayment: any;

@Component({
  selector: 'app-stripe',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss']
})
export class CheckoutComponent implements OnInit {
  @ViewChild(StripeCardComponent) card: StripeCardComponent;
  cardElement: any;
  private stripe: Stripe;
  currentUser: UserDTO = <UserDTO>{};
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

  async ngOnInit() {
    this.loadShoppingCart();
    this.loadOwnerOfShoppingCart();
    this.stripe = await loadStripe(environment.stripe);
    const elements = this.stripe.elements();
    this.cardElement = elements.create('card');
    this.cardElement.mount('#card');
    this.route.queryParams.subscribe(data => {
      this.clientSecret = data['client'];
    });

  }

  private loadShoppingCart() {
    this.serviceStripe.getShoppingcart().subscribe(data => {
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
    this.userService.getCurrentUser().subscribe(data => {
      this.currentUser = data;
      this.ownerOfShoppingCart = data.firstName;
    });
  }

  makePayment() {

  }
}

