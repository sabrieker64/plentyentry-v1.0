import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UserRoutingModule} from './user-routing.module';
import {RegisterComponent} from './register/register.component';
import {LoginComponent} from "./login/login.component";
import {CartComponent} from './cart/cart.component';
import {UserDetailComponent} from './user-detail/user-detail.component';
import {AngularMaterialModule} from "../../library/anguler-material-module/anguler-material-module.module";
import {AppRoutingModule} from "../app-routing.module";
import {FlexModule} from "@angular/flex-layout";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    RegisterComponent,
    LoginComponent,
    CartComponent,
    UserDetailComponent
  ],
  exports: [
    LoginComponent,
    CartComponent,
    UserDetailComponent,
    RegisterComponent
  ],

    imports: [
        CommonModule,
        UserRoutingModule,
        AngularMaterialModule,
        AppRoutingModule,
        FlexModule,
        FormsModule,
        ReactiveFormsModule
    ]
})
export class UserRegisterModule {
}
