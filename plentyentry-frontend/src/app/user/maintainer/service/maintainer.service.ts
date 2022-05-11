import { Injectable } from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {EventDTO, UserDTO} from "../../../definitions/objects";

@Injectable({
  providedIn: 'root'
})
export class MaintainerService {

  private baseUrl: string = environment.baseUrl + 'api/backend/event';

  constructor(private http: HttpClient) {
  }

  getMaintainedEvents() {
    return this.http.get<EventDTO[]>(`${this.baseUrl}/list/maintainedEvents`);
  }
}
