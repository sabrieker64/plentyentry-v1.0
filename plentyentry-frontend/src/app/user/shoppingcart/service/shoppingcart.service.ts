import {Injectable} from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient, HttpParams} from "@angular/common/http";
import {
  CheckoutSessionDTO,
  EmailSendDTO,
  EmailSendWithAttachmentDTO,
  ShoppingCartDTO,
  StripeCheckoutResultDTO,
  TicketDTO,
  TicketsToRemove
} from "../../../definitions/objects";
import {Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class ShoppingcartService {

  private baseUrl: string = environment.baseUrl + 'api/backend/shoppingcart';
  private baseUrlTicket: string = environment.baseUrl + 'api/backend/ticket';
  private baseUrlPayment: string = environment.baseUrl + 'api/backend/stripe/create-checkout-session';
  private baseUrlEmail: string = environment.baseUrl + 'api/backend/email';

  constructor(private http: HttpClient) {
  }


  getShoppingcart() {
    return this.http.get<ShoppingCartDTO>(`${this.baseUrl}`);
  }

  makePaymentWithCheckoutSession(checkoutDTO: CheckoutSessionDTO): Observable<StripeCheckoutResultDTO> {
    return this.http.post<StripeCheckoutResultDTO>(`${this.baseUrlPayment}`, checkoutDTO);
  }


  updateShoppingCart(ticketsToRemove: TicketsToRemove): Observable<TicketsToRemove> {
    return this.http.post<TicketsToRemove>(`${this.baseUrlTicket}` + '/deleteTicketsFromShoppingCart', ticketsToRemove);
  }


  sendEmailService(sendDetails: EmailSendDTO): Observable<any> {
    return this.http.post<any>(`${this.baseUrlEmail}`+ '/send-email', sendDetails);
  }

  sendEmailServiceWithAttachment(sendDetails: EmailSendWithAttachmentDTO): Observable<any> {
    return this.http.post<any>(`${this.baseUrlEmail}` + '/send-email/attachment', sendDetails);
  }


}
