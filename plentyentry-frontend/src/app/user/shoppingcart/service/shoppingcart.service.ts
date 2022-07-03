import {Injectable} from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {CheckoutSessionDTO, ShoppingCartDTO, StripeCheckoutResultDTO} from "../../../definitions/objects";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ShoppingcartService {

  private baseUrl: string = environment.baseUrl + 'api/backend/shoppingcart';

  private baseUrlPayment: string = environment.baseUrl + 'api/backend/stripe/create-checkout-session';

  constructor(private http: HttpClient) {
  }

  getShoppingcart() {
    return this.http.get<ShoppingCartDTO>(`${this.baseUrl}`);
  }

  makePaymentWithCheckoutSession(checkoutDTO: CheckoutSessionDTO): Observable<StripeCheckoutResultDTO> {
    return this.http.post<StripeCheckoutResultDTO>(`${this.baseUrlPayment}`, checkoutDTO);
  }


}
