import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {LoginRegisterRoutingModule} from './login-register-routing.module';
import {RegisterComponent} from './register/register.component';
import {LoginComponent} from "./login/login.component";


@NgModule({
  declarations: [
    RegisterComponent,
    LoginComponent
  ],
  imports: [
    CommonModule,
    LoginRegisterRoutingModule
  ]
})
export class LoginRegisterModule {
}
