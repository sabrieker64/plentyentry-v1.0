/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.32.889 on 2022-07-12 19:53:11.

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
    companyName: string;
    phoneNumber: string;
    uid: string;
    enabled: boolean;
}

export interface EventDTO {
    id: number;
    name: string;
    startDateTime: Date;
    endDateTime: Date;
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
    orderId: string;
}

export interface Order {
    price: number;
    currency: string;
    method: string;
    intent: string;
    description: string;
}

export interface CreateTokenDTO {
    cardNumber: number;
    expMonth: number;
    expYear: number;
    cvc: number;
}

export interface CheckoutSessionDTO {
    userId: number;
    successUrl: string;
    cancelUrl: string;
    fullAmount: number;
}

export interface StripeCheckoutResultDTO {
    urlToStripe: string;
}

export interface TicketsToRemove {
    eventId: number;
}

export interface EmailSendDTO {
    emailTo: string;
    email: string;
    subject: string;
}

export interface EmailSendWithAttachmentDTO {
    emailTo: string;
    email: string;
    subject: string;
    files: any[];
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
    tickets: ShoppingCartTicketDTOPerEvent[];
}

export interface Serializable {
}

export interface ShoppingCartTicketDTOPerEvent {
    ticketDTOS: TicketDTO[];
    quantity: number;
    amount: number;
    eventName: string;
    eventDescription: string;
    eventDate: Date;
    pricePerTicket: number;
}

export interface TicketDTO {
    id: number;
    quantity: number;
    ticketStatus: TicketStatus;
    qrCode: string;
    event: EventDTO;
    shoppingCart: ShoppingCartDTO;
}

export type UserType = "GUEST" | "CUSTOMER" | "ADMIN" | "MAINTAINER" | "SUPERADMIN";

export type UserGender = "MALE" | "FEMALE" | "DIVERSE";

export type Currency = "EUR";

export type StripePaymentTypes = "card";

export type PaymentType = "CREDIT_CARD" | "PAYPAL" | "BANK_TRANSFER";

export type TicketStatus = "NOTUSED" | "RESERVED" | "USED" | "INUSE" | "EXPIRED" | "SELLED" | "NOTSELLED";
