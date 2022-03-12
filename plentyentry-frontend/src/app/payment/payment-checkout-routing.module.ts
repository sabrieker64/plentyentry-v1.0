import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PaymentCheckoutComponent} from "./checkout/payment-checkout.component";

const routes: Routes = [
  {
    path: 'checkout',
    component: PaymentCheckoutComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PaymentCheckoutRoutingModule {
}
