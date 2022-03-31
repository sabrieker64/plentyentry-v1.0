import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../../environments/environment";
import {loadStripe} from "@stripe/stripe-js";
import {PaymentService} from "../../service/payment.service";

@Component({
  selector: 'app-stripe',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss']
})
export class CheckoutComponent {
  stripePromise = loadStripe(environment.stripe);


  constructor(private http: HttpClient, private service: PaymentService) {
  }

  async pay(): Promise<void> {
    // here we create a payment object
    const payment = {
      name: 'Iphone',
      currency: 'eur',
      // amount on cents *10 => to be on dollar
      amount: 99900,
      quantity: '1',
      cancelUrl: 'http://localhost:4200/payment/cancel',
      successUrl: 'http://localhost:4200/payment/success',
    };

    const stripe = await this.stripePromise;

    this.http
      .post(`${environment.baseUrl}api/backend/stripe/create-payment-intent`, payment)
      .subscribe((data: any) => {
        if (stripe) {
          stripe.redirectToCheckout({
            sessionId: data.id,
          });
        }
        return;
      });
  }
}
