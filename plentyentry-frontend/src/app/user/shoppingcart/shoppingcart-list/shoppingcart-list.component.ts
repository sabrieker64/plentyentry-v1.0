import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {MatTableDataSource} from "@angular/material/table";
import {ShoppingCartDTO, ShoppingCartTicketDTOPerEvent} from "../../../definitions/objects";
import {ShoppingcartService} from "../service/shoppingcart.service";

@Component({
  selector: 'app-shoppingcart-list',
  templateUrl: './shoppingcart-list.component.html',
  styleUrls: ['./shoppingcart-list.component.scss']
})
export class ShoppingcartListComponent implements OnInit {

  private loaded: boolean = false;
  eventId: number;
  theRealTicketList: ShoppingCartTicketDTOPerEvent[];
  ticketsRawList: ShoppingCartTicketDTOPerEvent[];

  constructor(private shoppincartService: ShoppingcartService, private router: Router) {
  }

  ngOnInit(): void {
    this.loadShoppingCart();
  }

  staticPositions: number = 1;
  displayedColumns: string[] = ['position', 'name', 'date', 'description', 'quantity', 'price', 'deleteTicket'];
  tickets: MatTableDataSource<ShoppingCartTicketDTOPerEvent>;
  ticketArrayCalculating: ShoppingCartTicketDTOPerEvent[];
  fullPrice: number = 0;
  shoppingcart: ShoppingCartDTO;

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.tickets.filter = filterValue.trim().toLowerCase();
  }

  loadShoppingCart() {
    //TODO diese verdammmte kacklogik (nichts gegen dich habub es geht nicht anders, aber diese logik will ich bitte
    // im backend im frontend ist mir das zu gefährlich
    return this.shoppincartService.getShoppingcart().subscribe(shoppingcart => {
      shoppingcart.tickets.forEach(ticket => {
        this.fullPrice = this.fullPrice + (ticket.quantity * ticket.ticketDTOS[0].event.price);
      });
      this.ticketsRawList = shoppingcart.tickets;
      console.log(this.ticketsRawList);
      this.tickets = new MatTableDataSource(this.ticketsRawList);
      this.ticketArrayCalculating = shoppingcart.tickets;
      if (this.tickets) {
        this.loaded = true;
      }
      console.log(this.shoppingcart.tickets);
    }, error => {
      console.log(error);
    });

  }

  deleteItem(id: number) {
    // todo exclude to ticket from the shoppingcart
  }

  goToCheckout() {
    //todo there we will going to checkout there is now time for payment
    this.router.navigateByUrl('/payment/stripe-checkout');
  }
}
