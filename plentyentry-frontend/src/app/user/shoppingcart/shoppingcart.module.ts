import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ShoppingcartRoutingModule } from './shoppingcart-routing.module';
import { ShoppingcartListComponent } from './shoppingcart-list/shoppingcart-list.component';
import {AngularMaterialModule} from "../../../library/anguler-material-module/anguler-material-module.module";


@NgModule({
  declarations: [
    ShoppingcartListComponent
  ],
  imports: [
    CommonModule,
    ShoppingcartRoutingModule,
    AngularMaterialModule
  ]
})
export class ShoppingcartModule { }
