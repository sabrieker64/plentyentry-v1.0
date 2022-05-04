import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StripeService {

  private stripejsUrl = 'https://js.stripe.com/v3/';

  constructor() {
  }

}
