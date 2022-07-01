import {Injectable} from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient, HttpParams} from "@angular/common/http";
import {ShoppingCartDTO, TicketDTO} from "../../../definitions/objects";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ShoppingcartService {

  private baseUrl: string = environment.baseUrl + 'api/backend/shoppingcart';
  private baseUrlTicket: string = environment.baseUrl + 'api/backend/ticket';

  constructor(private http: HttpClient) {
  }

  getShoppingcart() {
    return this.http.get<ShoppingCartDTO>(`${this.baseUrl}`);
  }


  updateShoppingCart(ticketId: number): Observable<TicketDTO[]> {
    var httpParams = new HttpParams();
    httpParams.append("ticketId", ticketId);
    return this.http.put<TicketDTO[]>(`${this.baseUrlTicket}` + '/removeFromShoppingCart', httpParams);
  }


}
