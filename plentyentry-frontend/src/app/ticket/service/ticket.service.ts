import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {TicketDTO} from "../../definitions/objects";
import {environment} from "../../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  private BASE_URL: string = environment.baseUrl + 'api/backend/ticket';
  private BASE_URL_QR: string = environment.baseUrl + 'api/backend/qrcode';

  constructor(private http: HttpClient) {
  }

  public getBoughtTickets(): Observable<TicketDTO[]> {
    return this.http.get<TicketDTO[]>(`${this.BASE_URL}` + '/boughtTickets');
  }

  public getTicketsWithQuantityRandom(eventId: number, quantity: number): Observable<TicketDTO[]> {
    return this.http.get<TicketDTO[]>(`${this.BASE_URL}` + "/" + eventId + "/" + quantity);
  }

  updateBoughtTicket(tickets: TicketDTO[]): Observable<any> {
    return this.http.put<any>(`${this.BASE_URL}/update-bought-tickets`, tickets);
  }

  sendUserTicketQRCode(tickets: TicketDTO[]): Observable<any> {
    return this.http.post<any>(`${this.BASE_URL_QR}/sendMail`, tickets);
  }

  public getQRCode(ticketID: number): Observable<ArrayBuffer> {
    const httpOptions: Object = {
      headers: new HttpHeaders({
        'Accept': 'text/html',
        'Content-Type': 'text/plain; charset=utf-8'
      }),
      responseType: 'blob'
    };

    return this.http.get<ArrayBuffer>(`${this.BASE_URL_QR}/` + ticketID,
      httpOptions);
  }

  public removeFromShoppingCart(ticketID: number): Observable<TicketDTO> {
    return this.http.put<TicketDTO>(`${this.BASE_URL}`, ticketID);
  }

  public findAllTicketsThatAreAvailable(eventId: number): Observable<TicketDTO[]> {
    return this.http.get<TicketDTO[]>(`${this.BASE_URL}`+ '/count-ticket-available?eventId=' + eventId);
  }

}
