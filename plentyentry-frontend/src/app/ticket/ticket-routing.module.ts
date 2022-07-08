import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {BoughtTicketListComponent} from "./ticket-list/bought-ticket-list.component";

const routes: Routes = [

  {
    path: 'bought-tickets',
    component: BoughtTicketListComponent
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TicketRoutingModule { }
