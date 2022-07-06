import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {TicketRoutingModule} from './ticket-routing.module';
import {TicketListComponent} from './ticket-list/ticket-list.component';
import {MatExpansionModule} from "@angular/material/expansion";
import {MatIconModule} from "@angular/material/icon";


@NgModule({
  declarations: [
    TicketListComponent
  ],
  imports: [
    CommonModule,
    TicketRoutingModule,
    MatExpansionModule,
    MatIconModule
  ]
})
export class TicketModule { }
