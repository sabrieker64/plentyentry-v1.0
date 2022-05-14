/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.32.889 on 2022-05-14 10:24:40.

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
    coronaStatus: CoronaStatusDTO;
    paymentMethod: PaymentMethodDTO[];
    jwtToken: string;
    shoppingCartDTO: ShoppingCartDTO;
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

export interface UserRegisterDTO {
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    city: string;
    postCode: string;
    street: string;
    birthday: Date;
    userGender: UserGender;
    shoppingCartDTO: ShoppingCartDTO;
}

export interface UserAuthReqDTO {
    email: string;
    password: string;
}

export interface PaymentIntentDTO extends Serializable {
  currency: Currency;
  amount: number;
  eventId: number;
  description: string;
  paymentType: StripePaymentTypes;
}

export interface Order {
  price: number;
  currency: string;
  method: string;
  intent: string;
  description: string;
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

export interface ShoppingCartDTO {
  id: number;
  tickets: TicketDTO[];
}

export interface Serializable {
}

export interface TicketDTO {
  id: number;
  quantity: number;
  ticketStatus: TicketStatus;
  qrCode: string;
  event: EventDTO;
}

export type UserType = "GUEST" | "CUSTOMER" | "ADMIN" | "MAINTAINER" | "SUPERADMIN";

export type UserGender = "MALE" | "FEMALE" | "DIVERSE";

export type Currency = "EUR";

export type StripePaymentTypes = "card" | "giropay" | "sepa_debit";

export type PaymentType = "CREDIT_CARD" | "PAYPAL" | "BANK_TRANSFER";

export type TicketStatus = "NOTUSED" | "USED" | "INUSE" | "EXPIRED" | "SELLED" | "NOTSELLED";
