import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
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
