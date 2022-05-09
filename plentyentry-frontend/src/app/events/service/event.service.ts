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

  constructor(private http: HttpClient) {
  }

  public getAllEvents(): Observable<EventDTO[]> {
    return this.http.get<EventDTO[]>(`${this.BASE_URL}` + '/list');
  }


  public getEventById(eventId: number): Observable<EventDTO> {
    return this.http.get<EventDTO>(`${this.BASE_URL}/` + eventId);
  }

  public updateEventById(eventDTO: EventDTO): Observable<EventDTO> {
    return this.http.put<EventDTO>(`${this.BASE_URL}`, eventDTO);
  }

  public createEvent(eventDTO: EventDTO): Observable<EventDTO> {
    return this.http.post<EventDTO>(`${this.BASE_URL}`, eventDTO);
  }
}
