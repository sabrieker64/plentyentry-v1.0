import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {UserDTO} from "../../definitions/objects";

@Injectable({
  providedIn: 'root'
})
export class UserDetailService {

  private baseUrl: string = environment.baseUrl + 'api/backend/user';

  //private baseUrl: string = "http://172.16.254.133/" + 'api/backend/user';


  constructor(private http: HttpClient) {
  }

  public getUserByJWT(): Observable<UserDTO> {
    return this.http.get<UserDTO>(`${this.baseUrl}`);
  }

  public updateUser(userDTO: UserDTO): Observable<UserDTO> {
    return this.http.put<UserDTO>(`${this.baseUrl}`, userDTO);
  }

//TODO: Mukiiii services erstellen
}
