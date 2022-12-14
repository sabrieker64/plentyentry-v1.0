import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {MatTableDataSource} from "@angular/material/table";
import {
  CheckoutSessionDTO,
  EmailSendWithAttachmentDTO,
  PaymentIntentDTO,
  ShoppingCartDTO,
  ShoppingCartTicketDTOPerEvent, StripeCheckoutResultDTO,
  TicketsToRemove
} from "../../../definitions/objects";
import {ShoppingcartService} from "../service/shoppingcart.service";
import {StripeService} from "../../../payment/stripe/stripe.service";
import {environment} from "../../../../environments/environment";
import {TicketService} from "../../../ticket/service/ticket.service";


@Component({
  selector: 'app-shoppingcart-list',
  templateUrl: './shoppingcart-list.component.html',
  styleUrls: ['./shoppingcart-list.component.scss']
})
export class ShoppingcartListComponent implements OnInit {

  private loaded: boolean = false;
  eventId: number;
  paymenIntent: PaymentIntentDTO = <PaymentIntentDTO>{};
  theRealTicketList: ShoppingCartTicketDTOPerEvent[];
  ticketsRawList: ShoppingCartTicketDTOPerEvent[];
  checkoutDTO: CheckoutSessionDTO = <CheckoutSessionDTO>{};
  ticketsToRemove: TicketsToRemove = <TicketsToRemove>{};
  sendDetails: EmailSendWithAttachmentDTO = <EmailSendWithAttachmentDTO>{};

  constructor(private shoppincartService: ShoppingcartService,
              private router: Router, private stripeService: StripeService, private ticketService: TicketService) {
  }

  ngOnInit(): void {
    this.loadShoppingCart();
    // Create a media condition that targets viewports at least 768px wide
    const mediaQuery = window.matchMedia('(max-width: 600px)')
    // Check if the media query is true
    if (mediaQuery.matches) {
      this.displayedColumns = ['name', 'quantity', 'price', 'deleteTicket'];
    }
  }

  staticPositions: number = 1;
  displayedColumns: string[] = ['position', 'name', 'date', 'quantity', 'price', 'deleteTicket'];
  tickets: MatTableDataSource<ShoppingCartTicketDTOPerEvent>;
  ticketArrayCalculating: ShoppingCartTicketDTOPerEvent[];
  fullPrice: number = 0;
  shoppingcart: ShoppingCartDTO;
  checkIfAnythingisInSC: boolean;
  resultOfStripe: StripeCheckoutResultDTO = <StripeCheckoutResultDTO>{};

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.tickets.filter = filterValue.trim().toLowerCase();
  }

  loadShoppingCart() {
    return this.shoppincartService.getShoppingcart().subscribe(shoppingcart => {
      if(shoppingcart.tickets.length == 0){
        this.checkIfAnythingisInSC = false;
      }else {
        this.checkIfAnythingisInSC = true;
      }
      shoppingcart.tickets.forEach(ticket => {
        this.fullPrice = this.fullPrice + (ticket.quantity * ticket.ticketDTOS[0].event.price);
      });
      this.ticketsRawList = shoppingcart.tickets;

      // console.log(this.ticketsRawList);
      this.tickets = new MatTableDataSource(this.ticketsRawList);
      this.ticketArrayCalculating = shoppingcart.tickets;
      if (this.tickets) {
        this.loaded = true;
      }
      //console.log(this.shoppingcart?.tickets);
    }, error => {
      console.log(error);
    });

  }

  deleteItem(shoppingcart: ShoppingCartTicketDTOPerEvent) {
    console.log(shoppingcart);
    this.ticketsToRemove.eventId = shoppingcart.ticketDTOS[0].event.id;
    this.shoppincartService.updateShoppingCart(this.ticketsToRemove).toPromise().then(data => {
      console.log(data.eventId);
      location.reload();
    });

  }

  goToCheckout(fullPrice: number) {
    this.paymenIntent.amount = fullPrice;
    this.paymenIntent.currency = "EUR";
    this.checkoutDTO.fullAmount = this.fullPrice;
    this.checkoutDTO.cancelUrl = environment.frontendBaseUrl + '/payment/cancel';
    this.checkoutDTO.successUrl = environment.frontendBaseUrl + '/payment/success';
    this.shoppincartService.makePaymentWithCheckoutSession(this.checkoutDTO)
      .subscribe(result => {
        this.resultOfStripe = result;
        window.location.href = result.urlToStripe;
    });
  }
}
