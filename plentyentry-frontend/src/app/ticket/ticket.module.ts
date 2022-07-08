import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TicketRoutingModule} from './ticket-routing.module';
import {MatExpansionModule} from "@angular/material/expansion";
import {MatIconModule} from "@angular/material/icon";
import {BoughtTicketListComponent} from "./ticket-list/bought-ticket-list.component";


@NgModule({
  declarations: [
    BoughtTicketListComponent
  ],
  imports: [
    CommonModule,
    TicketRoutingModule,
    MatExpansionModule,
    MatIconModule
  ]
})
export class TicketModule { }
