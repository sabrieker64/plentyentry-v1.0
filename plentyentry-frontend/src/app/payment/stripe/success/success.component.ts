import {Component, OnInit} from '@angular/core';
import {ShoppingcartService} from "../../../user/shoppingcart/service/shoppingcart.service";
import {ShoppingCartDTO, ShoppingCartTicketDTOPerEvent, TicketDTO} from "../../../definitions/objects";
import {TicketService} from "../../../ticket/service/ticket.service";

@Component({
  selector: 'app-success',
  templateUrl: './success.component.html',
  styleUrls: ['./success.component.scss']
})
export class SuccessComponent implements OnInit {
  currentShoppingCart: ShoppingCartDTO = <ShoppingCartDTO>{};
  tickets: TicketDTO[] = <TicketDTO[]>{};

  constructor(private shoppingCartService: ShoppingcartService, private ticketService: TicketService) {
  }

  ngOnInit(): void {
    this.loadShoppingCart();
    /* this.sendEmail();*/
  }

  sendEmail(eachTicketEvent: ShoppingCartTicketDTOPerEvent) {
    this.ticketService.sendUserTicketQRCode(eachTicketEvent.ticketDTOS).toPromise().then(data => {
    });
  }

  private loadShoppingCart() {
    this.shoppingCartService.getShoppingcart().toPromise().then(data => {
      this.currentShoppingCart = data;
      this.currentShoppingCart.tickets.forEach(tickets => {
        this.tickets = tickets.ticketDTOS;
      });
      this.currentShoppingCart.tickets.forEach(eachTicketEvent => {
        this.ticketService.updateBoughtTicket(eachTicketEvent.ticketDTOS
          .filter(ticket => ticket.ticketStatus === 'RESERVED')).toPromise().then(() => {});
        this.sendEmail(eachTicketEvent);
      });
    });
  }
}
