import { Injectable } from '@angular/core';
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

  constructor(private http: HttpClient) { }

  public getBoughtTickets(): Observable<TicketDTO[]> {
    return this.http.get<TicketDTO[]>(`${this.BASE_URL}` + '/boughtTickets');
  }

  public getQRCode(ticketID: number): Observable<ArrayBuffer> {


    const httpOptions : Object = {
      headers: new HttpHeaders({
        'Accept': 'text/html',
        'Content-Type': 'text/plain; charset=utf-8'
      }),
      responseType: 'blob'
    };

    return this.http.get<ArrayBuffer>(`${this.BASE_URL_QR}/` + ticketID,
      httpOptions);
  }

}
