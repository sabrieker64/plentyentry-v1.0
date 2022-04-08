import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PaymentCheckoutComponent} from "./checkout/payment-checkout.component";
import {CheckoutComponent} from "./stripe/checkout/checkout.component";
import {SuccessComponent} from "./stripe/success/success.component";
import {CancelComponent} from "./stripe/cancel/cancel.component";

const routes: Routes = [
  {
    path: 'paypal-checkout',
    component: PaymentCheckoutComponent
  },
  {
    path: 'stripe-checkout',
    component: CheckoutComponent
  },
  {
    path: 'success',
    component: SuccessComponent
  },
  {
    path: 'cancel',
    component: CancelComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PaymentCheckoutRoutingModule {
}
