import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {PaymentCheckoutRoutingModule} from './payment-checkout-routing.module';
import {CheckoutComponent} from './stripe/checkout/checkout.component';
import {SuccessComponent} from './stripe/success/success.component';
import {CancelComponent} from './stripe/cancel/cancel.component';
import {AngularMaterialModule} from "../../library/anguler-material-module/anguler-material-module.module";
import {NgxStripeModule} from "ngx-stripe";


@NgModule({
  declarations: [
    CheckoutComponent,
    SuccessComponent,
    CancelComponent
  ],
  imports: [
    CommonModule,
    AngularMaterialModule,
    PaymentCheckoutRoutingModule,
    NgxStripeModule
  ]
})
export class PaymentCheckoutModule {
}
