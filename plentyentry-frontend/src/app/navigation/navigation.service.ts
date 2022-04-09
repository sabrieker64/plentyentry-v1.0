import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class NavigationService {

  private baseUrl: string = environment.baseUrl + 'api/backend/user';

  constructor(private http: HttpClient) {
  }

  public confirmToken(): Observable<String> {
    return this.http.get<String>(`${this.baseUrl}` + '/confirm');
  }
}
