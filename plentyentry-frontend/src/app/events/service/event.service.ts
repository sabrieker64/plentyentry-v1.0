import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {EventDTO, TicketDTO} from "../../definitions/objects";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})

export class EventService {

  private BASE_URL: string = environment.baseUrl + 'api/backend/event';
  private BASE_URL_TICKET: string = environment.baseUrl + 'api/backend/ticket';

  constructor(private http: HttpClient) {
  }

  public getAllEvents(): Observable<EventDTO[]> {
    return this.http.get<EventDTO[]>(`${this.BASE_URL}` + '/list');
  }


  public getEventById(eventId: number): Observable<EventDTO> {
    return this.http.get<EventDTO>(`${this.BASE_URL}/` + eventId);
  }

  public updateEventById(eventDTO: EventDTO): Observable<EventDTO> {
    return this.http.put<EventDTO>(`${this.BASE_URL}/special-privileges`, eventDTO);
  }

  public createEvent(eventDTO: EventDTO): Observable<EventDTO> {
    return this.http.post<EventDTO>(`${this.BASE_URL}/special-privileges`, eventDTO);
  }

  public addTicketsToShoppingCart(ticketsDTO: TicketDTO[]): Observable<any> {
    return this.http.put<any>(`${this.BASE_URL_TICKET}/addToShoppingCart`, ticketsDTO);
  }

  public selectTicketsAndAddToCustomerShoppingCart(eventId: number, quantity: number): Observable<TicketDTO[]> {
    return this.http.get<TicketDTO[]>(`${this.BASE_URL_TICKET}/selectTicketsAndAddToCart/` + eventId + "/" + quantity);
  }
}
