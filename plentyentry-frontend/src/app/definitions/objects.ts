/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.32.889 on 2022-02-06 19:36:25.

export interface UserDTO {
  id: number;
  firstName: string;
  lastName: string;
  street: string;
  email: string;
  password: string;
  postCode: string;
  city: string;
  age: number;
  svNumber: number;
  birthday: Date;
  userType: UserType;
  userGender: UserGender;
  events: EventDTO[];
  tickets: TicketDTO[];
  coronaStatus: CoronaStatusDTO;
  paymentMethod: PaymentMethodDTO[];
  jwtToken: string;
}

export interface EventDTO {
  id: number;
  name: string;
  date: Date;
  description: string;
  price: number;
  ticketCounter: number;
  ticketId: number;
  city: string;
  address: string;
  eventImageUrls: string[];
  entertainers: UserDTO[];
}

export interface TicketDTO {
    id: number;
    quantity: number;
    ticketStatus: TicketStatus;
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

export type UserType = "GUEST" | "CUSTOMER" | "ADMIN" | "MAINTAINER" | "SUPERADMIN";

export type UserGender = "MALE" | "FEMALE" | "DIVERSE";

export type TicketStatus = "NOTUSED" | "USED" | "INUSE" | "EXPIRED" | "SELLED" | "NOTSELLED";

export type PaymentType = "CREDIT_CARD" | "PAYPAL" | "BANK_TRANSFER";
