import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CreateTokenDTO, PaymentIntentDTO, ShoppingCartDTO} from "../../definitions/objects";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class StripeService {

  private stripejsUrl = 'https://js.stripe.com/v3/';
  private baseUrl: string = environment.baseUrl + 'api/backend/shoppingcart';
  private BASE_URL = environment.baseUrl + 'api/backend/stripe';

  constructor(private http: HttpClient) {
  }

  public makePaymentWithCreateToken(createToken: CreateTokenDTO): Observable<any> {
    return this.http.post<any>(`${this.BASE_URL}/create-token`, createToken);
  }

  public makePaymentIntent(paymentIntent: PaymentIntentDTO): Observable<any> {
    return this.http.post<any>(`${this.BASE_URL}/create-payment-intent`, paymentIntent);
  }


  public getShoppingcart(): Observable<ShoppingCartDTO> {
    return this.http.get<ShoppingCartDTO>(`${this.baseUrl}`);
  }

  public confirmPayment(id: string): Observable<any> {
    return this.http.post<any>(`${this.BASE_URL}/confirm/` + id, null);

  }
}
