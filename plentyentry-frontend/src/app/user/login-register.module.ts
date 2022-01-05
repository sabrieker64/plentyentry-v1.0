import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {LoginRegisterRoutingModule} from './login-register-routing.module';
import {RegisterComponent} from './register/register.component';
import {LoginComponent} from "./login/login.component";
import {CartComponent} from './cart/cart.component';
import {UserDetailComponent} from './user-detail/user-detail.component';


@NgModule({
  declarations: [
    RegisterComponent,
    LoginComponent,
    CartComponent,
    UserDetailComponent
  ],
  imports: [
    CommonModule,
    LoginRegisterRoutingModule
  ]
})
export class LoginRegisterModule {
}
