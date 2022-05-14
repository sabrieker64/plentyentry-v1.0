import { Component, OnInit } from '@angular/core';
import {MaintainerService} from "../../maintainer/service/maintainer.service";
import {Router} from "@angular/router";
import {EventService} from "../../../events/service/event.service";
import {MatTableDataSource} from "@angular/material/table";
import {EventDTO, ShoppingCartDTO, TicketDTO} from "../../../definitions/objects";
import {ShoppingcartService} from "../service/shoppingcart.service";

@Component({
  selector: 'app-shoppingcart-list',
  templateUrl: './shoppingcart-list.component.html',
  styleUrls: ['./shoppingcart-list.component.scss']
})
export class ShoppingcartListComponent implements OnInit {

  private loaded: boolean = false;

  constructor(private shoppincartService: ShoppingcartService, private router: Router) {
  }

  ngOnInit(): void {
    this.loadShoppingCart();
  }

  staticPositions: number = 1;
  displayedColumns: string[] = ['position', 'name', 'date', 'description', 'quantity', 'price', 'quantitiyPrice', 'deleteTicket'];
  tickets: MatTableDataSource<TicketDTO>;
  ticketArrayCalculating: TicketDTO[];
  fullPrice: number = 0;
  shoppingcart: ShoppingCartDTO;

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.tickets.filter = filterValue.trim().toLowerCase();
  }

  loadShoppingCart() {
    return this.shoppincartService.getShoppingcart().subscribe(shoppingcart => {
      this.shoppingcart = shoppingcart;
      this.tickets = new MatTableDataSource(this.shoppingcart.tickets.filter(ticket => ticket.ticketStatus === "NOTUSED"));
      this.ticketArrayCalculating = this.shoppingcart.tickets.filter(ticket => ticket.ticketStatus === "NOTUSED");

      this.ticketArrayCalculating.forEach(ticket=>{
        this.fullPrice = this.fullPrice + (ticket.quantity * ticket.event.price);
      });

      if(this.tickets) {
        this.loaded = true;
      }


    }, error => {
      console.log(error);
    });

  }

  deleteItem(id: number){

    // TODO DELETE Item



  }

}
