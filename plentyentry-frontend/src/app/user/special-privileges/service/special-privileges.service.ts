import {Injectable} from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {UserDTO} from "../../../definitions/objects";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SpecialPrivilegesService {
  private baseUrlUser: string = environment.baseUrl + 'api/backend/user/special-privileges';


  constructor(private http: HttpClient) {
  }

  getAllUser(): Observable<UserDTO[]> {
    return this.http.get<UserDTO[]>(`${this.baseUrlUser}/list`);
  }

  public updateUser(userDTO: UserDTO): Observable<UserDTO> {
    return this.http.put<UserDTO>(`${this.baseUrlUser}`, userDTO);
  }

  public getUserById(userId: number): Observable<UserDTO> {
    return this.http.get<UserDTO>(`${this.baseUrlUser}/` + userId);
  }

}
