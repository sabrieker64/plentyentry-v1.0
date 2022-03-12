import {Injectable} from '@angular/core';

@Injectable()
export class AuthService {
  constructor() {
  }

  getToken(): string {
    let token = localStorage.getItem('token');
    if (token === null) {
      return "No token";
    }
    return token;
  }

}
