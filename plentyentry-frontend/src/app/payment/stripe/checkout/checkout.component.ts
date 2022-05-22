import {Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../../environments/environment";
import {loadStripe, StripeCardElementOptions, StripeElementsOptions} from "@stripe/stripe-js";
import {PaymentService} from "../../service/payment.service";
import {StripeCardNumberComponent, StripeElementsService} from "ngx-stripe";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import {Observable} from "rxjs";
import {ShoppingCartDTO} from "../../../definitions/objects";
import {UserDetailService} from "../../../user/service/user-detail.service";

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
  eventId: string = "Keine Ahnung";
  ownerOfShoppingCart: string = " Noch Keiner ";
  quantity: string;
  calculatedAmount: number;
  shoppingCartObserver: Observable<ShoppingCartDTO>;
  shoppingCart: ShoppingCartDTO;


  //todo hier muss ich das objekt im unserm fall das event das sich der kunde angeschaut und auf bezahlen geklickt hat
  //todo achtung ganz wichtig es kann auch aus dem warenkorb kommen mit einer listen von events oder

  constructor(private http: HttpClient, private service: PaymentService,
              private stripeService: StripeElementsService, private fb: FormBuilder,
              private route: ActivatedRoute, private userService: UserDetailService) {
  }

  ngOnInit(): void {
    this.stripeForm = this.fb.group({
      name: ['', [Validators.required]]
    });
    this.loadShoppingCart();
    console.log(this.shoppingCart);
    /* const routeParamEvent = this.route.paramMap.pipe(map(value => this.handleParams(value)));*/
    /* const loadPaymentObject = routeParamEvent.pipe(switchMap(() => this.loadEvent()));
     this.shoppingCartObserver = loadPaymentObject.pipe(
       map(value => this.complete(value)),
       catchError(() => this.handleError()),
       shareReplay(1));*/
  }


  public createCharge() {
  }

  makePayment() {
    console.log('test payment');
    //todo make rest call to stripe api endpoint
    //todo after creating the payment object, confirm or cancel the paymnet
    // todo 1.Schritt wir holen uns die shoppingcart von dem user und rechnen alle kaufmännisch gerundet zusammen
    //  danach gibt er uns seine daten in unserem fall entweder paypal oder debit/credit karte

  }

  getToken() {

  }

  createToken() {

  }

  /*  private handleParams(params: ParamMap) {
      const ticketId = params.get('');
      return params;

    }

    private loadEvent(): Observable<ShoppingCartDTO> {

      return of();
    }

    private complete(value: ShoppingCartDTO) {
      this.shoppingCart = value;
      return value;
    }

    private handleError() {
      return of(<ShoppingCartDTO>{});
    }*/
  private loadShoppingCart() {
    this.userService.getCurrentUser().toPromise().then(data => {
      console.log(data);
      this.ownerOfShoppingCart = data.firstName;
      this.shoppingCart = data.shoppingCartDTO;
      console.log(this.shoppingCart);
    });
  }
}
