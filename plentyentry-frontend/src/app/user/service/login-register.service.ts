import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {UserDTO, UserRegisterDTO} from "../../definitions/objects";

@Injectable({
  providedIn: 'root'
})
export class LoginRegisterService {
  regex: {
    passwort: '^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$',
    email: '^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$'
  }

  message: {
  noEmptyField: 'Dieses Feld darf nicht leer sein',
  noNumbers: 'Dieses Feld darf keine Ziffern enthalten',
  noLetters: 'Dieses Feld darf keine Buhstaben enthalten',
  wrongPattern: 'Bitte die Vorschriften für dieses Feld beachten',
  }

  private baseUrl: string = environment.baseUrl + 'api/backend/user';

  constructor(private http: HttpClient) {
  }

  public getFirstRestCall(id: number): Observable<UserDTO> {
    return this.http.get<UserDTO>(`${this.baseUrl}/${id}`);
  }

  //Muki das ist dein Register Rest Api
  public registerNewUser(userRegisterDTO: UserRegisterDTO): Observable<UserDTO> {
    return this.http.post<UserDTO>(`${this.baseUrl}` + '/register', userRegisterDTO);
  }

//TODO: Mukiiii services erstellen
}

export interface Errormesages {
  errorMessages: {
    noEmptyField: 'Dieses Feld darf nicht leer sein',
    noNumbers: 'Dieses Feld darf keine Ziffern enthalten',
    noLetters: 'Dieses Feld darf keine Buhstaben enthalten',
    wrongPattern: 'Bitte die Vorschriften für dieses Feld beachten',
  }
}
