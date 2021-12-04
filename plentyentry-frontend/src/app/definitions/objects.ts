/* tslint:disable */
/* eslint-disable */

// Generated using typescript-generator version 2.32.889 on 2021-12-02 20:55:01.

export interface UserDTO {
  id: number;
  firstName: string;
  lastName: string;
  street: string;
  postCode: string;
  city: string;
  age: number;
  svNumber: number;
  birthday: Date;
  tickets: string[];
  coronaStatus: string[];
  paymentMethod: string[];
}

export interface EventDTO {
  id: number;
  name: string;
  date: Date;
  description: string;
  price: number;
  ticketCounter: number;
  ticket: string[];
}
