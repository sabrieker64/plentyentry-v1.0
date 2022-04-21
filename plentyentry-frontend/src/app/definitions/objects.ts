/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.32.889 on 2022-04-21 22:26:01.

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

export interface CreatePaymentResponse {
    clientSecret: string;
}

export interface CreatePayment {
    items: any[];
}

export interface TicketDTO {
    id: number;
    quantity: number;
    ticketStatus: TicketStatus;
    user: UserDTO;
    event: EventDTO;
    shoppingCart: ShoppingCartDTO;
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
    user: UserDTO;
    tickets: TicketDTO[];
}

export type UserType = "GUEST" | "CUSTOMER" | "ADMIN" | "MAINTAINER" | "SUPERADMIN";

export type UserGender = "MALE" | "FEMALE" | "DIVERSE";

export type TicketStatus = "NOTUSED" | "USED" | "INUSE" | "EXPIRED" | "SELLED" | "NOTSELLED";

export type PaymentType = "CREDIT_CARD" | "PAYPAL" | "BANK_TRANSFER";
