import {Component, OnInit} from '@angular/core';
import {ShoppingcartService} from "../../../user/shoppingcart/service/shoppingcart.service";
import {ShoppingCartDTO, TicketDTO} from "../../../definitions/objects";
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

  private loadShoppingCart() {
    this.shoppingCartService.getShoppingcart().toPromise().then(data => {
      this.currentShoppingCart = data;

      console.log(this.currentShoppingCart.tickets.forEach(ticket => {
        console.log(ticket.ticketDTOS)
      }));
      this.currentShoppingCart.tickets.forEach(tickets => {
        this.tickets = tickets.ticketDTOS;
      })
    this.currentShoppingCart.tickets.forEach(eachTicketEvent => {
        this.ticketService.updateBoughtTicket(eachTicketEvent.ticketDTOS).toPromise().then(data => {
          console.log("Tickets Erfolgreich gekauft");

          this.ticketService.sendUserTicketQRCode(eachTicketEvent.ticketDTOS).toPromise().then(data => {

          });


        });
      });
    });
  }

/*   sendEmail() {
      this.tickets.forEach(ticket => {
          this.ticketService.getQRCode(ticket.id).
          subscribe((base64: ArrayBuffer) => {
            var blob  =  new Blob([base64]);
            var reader = new FileReader();
            reader.readAsArrayBuffer(blob);
            console.log(reader.readAsArrayBuffer(blob));
            var file = new File([blob], ticket.event.name );
            console.log(file);
          });
        });
      // this.sendDetails.files.push()
      // this.shoppincartService.sendEmailServiceWithAttachment()
    }*/
  }
