import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {UserDTO} from "../../definitions/objects";

@Injectable({
  providedIn: 'root'
})
export class LoginRegisterService {

  private baseUrl: string = environment.baseUrl + 'api/backend/user';

  constructor(private http: HttpClient) {
  }


  public getFirstRestCall(id: number): Observable<UserDTO> {
    return this.http.get<UserDTO>(`${this.baseUrl}/${id}`);
  }

//TODO: Mukiiii services erstellen
}
