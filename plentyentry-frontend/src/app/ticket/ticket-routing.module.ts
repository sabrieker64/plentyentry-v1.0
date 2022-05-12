import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {TicketListComponent} from "./ticket-list/ticket-list.component";

const routes: Routes = [

  {
    path: 'bought-tickets',
    component: TicketListComponent
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TicketRoutingModule { }
