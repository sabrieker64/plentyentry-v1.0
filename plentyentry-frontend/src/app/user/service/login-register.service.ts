import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {UserDTO, UserRegisterDTO} from "../../definitions/objects";

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

  public login(username:string, password:string) {
    this.http.post('http://httpbin.org/post', {username: username, password: password}).toPromise().then( data => {
      console.log(data);
    })
  }

  public register(gender: string, firstname: string, lastname: string, birthday: string, email: string, svNumber: string, adress: string, zipCode: string, city: string) {
    this.http.post('http://httpbin.org/post', {
      gender: gender,
      firstname: firstname,
      lastname: lastname,
      birthday: birthday,
      email: email,
      svNumber: svNumber,
      adress: adress,
      zipCode: zipCode,
      city: city
    }).toPromise().then(data => {
      console.log(data);
    })
  }

  //Muzki das ist dein Register Rest Api
  public registerNewUser(userRegisterDTO: UserRegisterDTO): Observable<UserDTO> {
    return this.http.post<UserDTO>(`${this.baseUrl}` + '/register', userRegisterDTO);
  }


//TODO: Mukiiii services erstellen
}
