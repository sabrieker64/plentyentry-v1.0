/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.32.889 on 2021-12-13 21:34:09.

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
  tickets: TicketDTO[];
  coronaStatus: CoronaStatusDTO[];
  paymentMethod: PaymentMethodDTO[];
}

export interface EventDTO {
  id: number;
  name: string;
  date: Date;
  description: string;
  price: number;
  ticketCounter: number;
  ticketId: number;
}

export interface TicketDTO {
  id: number;
  quantity: number;
  activity: boolean;
  user: UserDTO;
  event: EventDTO;
}

export interface CoronaStatusDTO {
  id: number;
  pcrTest: boolean;
  antigenTest: boolean;
  firstVaccine: boolean;
  secondVaccine: boolean;
  thirdVaccine: boolean;
  quarantine: boolean;
  user: UserDTO;
}

export interface PaymentMethodDTO {
  id: number;
  paymentType: PaymentType;
  creditCardNumber: number;
  nameOnCard: string;
  cvSecurityCode: number;
  iban: number;
  user: UserDTO;
}

export type PaymentType = "CREDIT_CARD" | "PAYPAL" | "BANK_TRANSFER";
