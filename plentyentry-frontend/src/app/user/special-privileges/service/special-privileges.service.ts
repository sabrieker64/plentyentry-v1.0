import {Injectable} from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {UserDTO} from "../../../definitions/objects";

@Injectable({
  providedIn: 'root'
})
export class SpecialPrivilegesService {
  private baseUrlUser: string = environment.baseUrl + 'api/backend/user';


  constructor(private http: HttpClient) {
  }

  getAllUser() {
    return this.http.get<UserDTO[]>(`${this.baseUrlUser}/special-privileges/list`);
  }

}
