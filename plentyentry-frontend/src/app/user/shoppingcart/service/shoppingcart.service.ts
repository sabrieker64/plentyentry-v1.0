import { Injectable } from '@angular/core';
import {environment} from "../../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {EventDTO, ShoppingCartDTO} from "../../../definitions/objects";

@Injectable({
  providedIn: 'root'
})
export class ShoppingcartService {

  private baseUrl: string = environment.baseUrl + 'api/backend/shoppingcart';

  constructor(private http: HttpClient) { }

  getShoppingcart() {
    return this.http.get<ShoppingCartDTO>(`${this.baseUrl}`);
  }


}
