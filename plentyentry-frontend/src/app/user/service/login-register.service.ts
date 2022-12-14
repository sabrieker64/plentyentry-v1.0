import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {UserAuthReqDTO, UserDTO, UserRegisterDTO} from "../../definitions/objects";

@Injectable({
  providedIn: 'root'
})
export class LoginRegisterService {
  regex: {passwort: string, email: string} = {
    passwort: '^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$',
    email: '^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$'
  }

  private baseUrl: string = environment.baseUrl + 'api/backend/user';
  //private baseUrl: string = "http://172.16.254.133/" + 'api/backend/user';

  constructor(private http: HttpClient) {
  }

  public getFirstRestCall(id: number): Observable<UserDTO> {
    return this.http.get<UserDTO>(`${this.baseUrl}/${id}`);
  }

  public getUserByEmail(email: string): Observable<UserDTO> {
    return this.http.get<UserDTO>(`${this.baseUrl}/by-email/${email}`);
  }

  public registerNewUser(userRegisterDTO: UserRegisterDTO): Observable<UserDTO> {
    return this.http.post<UserDTO>(`${this.baseUrl}` + '/register', userRegisterDTO);
  }

  public authenticateUser(userAuthReqDTO: UserAuthReqDTO): Observable<UserDTO> {
    return this.http.post<UserDTO>(`${this.baseUrl}` + '/authenticate', userAuthReqDTO);
  }

  public getUserByJWT(): Observable<UserDTO> {
    return this.http.get<UserDTO>(`${this.baseUrl}`);
  }

  public resetPassword(userAuthReqDTO: UserAuthReqDTO): Observable<UserDTO> {
    return this.http.post<UserDTO>(`${this.baseUrl}` + '/resetPassword', userAuthReqDTO);
  }

}
