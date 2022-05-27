import {Injectable} from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {EventDTO} from "../../../definitions/objects";

@Injectable({
  providedIn: 'root'
})
export class MaintainerService {

  private baseUrl: string = environment.baseUrl + 'api/backend/event/special-privileges';
  private baseUrlQRCode: string = environment.baseUrl + 'api/backend/qrcode';
  //private baseUrl: string = "http://172.16.254.133/" + 'api/backend/event';

  constructor(private http: HttpClient) {
  }

  getMaintainedEvents() {
    return this.http.get<EventDTO[]>(`${this.baseUrl}/list/maintainedEvents`);
  }

  scanTicket(ticketID: number) {
    return this.http.get<string>(`${this.baseUrlQRCode}/scan/`+ticketID);
  }
}
