import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MaintainedEventEditComponent} from "../maintainer/maintained-event-edit/maintained-event-edit.component";
import {ShoppingcartModule} from "./shoppingcart.module";
import {ShoppingcartListComponent} from "./shoppingcart-list/shoppingcart-list.component";

const routes: Routes = [

  {
    path: 'list',
    component: ShoppingcartListComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ShoppingcartRoutingModule { }
