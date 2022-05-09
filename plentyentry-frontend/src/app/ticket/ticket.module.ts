import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TicketRoutingModule } from './ticket-routing.module';
import { TicketListComponent } from './ticket-list/ticket-list.component';
import {MatExpansionModule} from "@angular/material/expansion";


@NgModule({
  declarations: [
    TicketListComponent
  ],
  imports: [
    CommonModule,
    TicketRoutingModule,
    MatExpansionModule
  ]
})
export class TicketModule { }
