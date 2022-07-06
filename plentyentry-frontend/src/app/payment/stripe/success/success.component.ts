import {Component, OnInit} from '@angular/core';
import {ShoppingcartService} from "../../../user/shoppingcart/service/shoppingcart.service";
import {ShoppingCartDTO} from "../../../definitions/objects";
import {TicketService} from "../../../ticket/service/ticket.service";

@Component({
  selector: 'app-success',
  templateUrl: './success.component.html',
  styleUrls: ['./success.component.scss']
})
export class SuccessComponent implements OnInit {
  currentShoppingCart: ShoppingCartDTO = <ShoppingCartDTO>{};

  constructor(private shoppingCartService: ShoppingcartService, private ticketService: TicketService) {
  }

  ngOnInit(): void {
    this.loadShoppingCart();
  }

  private loadShoppingCart() {
    this.shoppingCartService.getShoppingcart().toPromise().then(data => {
      this.currentShoppingCart = data;
      console.log(this.currentShoppingCart.tickets.forEach(ticket => {
        console.log(ticket.ticketDTOS)
      }));
      this.currentShoppingCart.tickets.forEach(eachTicketEvent => {
        this.ticketService.updateBoughtTicket(eachTicketEvent.ticketDTOS).toPromise().then(data => {
          console.log("Tickets Erfolgreich gekauft");
        });
      });
    });

  }
}
