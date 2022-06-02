import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {PaymentCheckoutRoutingModule} from './payment-checkout-routing.module';
import {CheckoutComponent} from './stripe/checkout/checkout.component';
import {SuccessComponent} from './stripe/success/success.component';
import {CancelComponent} from './stripe/cancel/cancel.component';
import {AngularMaterialModule} from "../../library/anguler-material-module/anguler-material-module.module";
import {NgxStripeModule} from "ngx-stripe";
import {environment} from "../../environments/environment";
import {ReactiveFormsModule} from "@angular/forms";


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
    NgxStripeModule.forRoot(environment.stripe),
    ReactiveFormsModule
  ]
})
export class PaymentCheckoutModule {
}
