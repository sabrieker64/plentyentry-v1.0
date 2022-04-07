import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {CreatePayment, CreatePaymentResponse} from "../../definitions/objects";

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  private baseUrl: string = environment.baseUrl + 'api/backend/stripe';

  constructor(private http: HttpClient) {
  }

  public makePayment(createPayment: CreatePayment): Observable<CreatePaymentResponse> {
    return this.http.post<CreatePaymentResponse>(`${this.baseUrl}/create-payment-intent`, createPayment);
  }
}
