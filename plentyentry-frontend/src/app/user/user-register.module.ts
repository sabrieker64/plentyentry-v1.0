import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UserRoutingModule} from './user-routing.module';
import {RegisterComponent} from './register/register.component';
import {LoginComponent} from "./login/login.component";
import {UserDetailComponent} from './user-detail/user-detail.component';
import {AngularMaterialModule} from "../../library/anguler-material-module/anguler-material-module.module";
import {AppRoutingModule} from "../app-routing.module";
import {FlexModule} from "@angular/flex-layout";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {LogoutComponent} from './logout/logout.component';
import {PasswordResetComponent} from './password-reset/password-reset.component';
import { EmailConfirmationComponent } from './email-confirmation/email-confirmation.component';


@NgModule({
  declarations: [
    RegisterComponent,
    LoginComponent,
    UserDetailComponent,
    LogoutComponent,
    PasswordResetComponent,
    EmailConfirmationComponent
  ],
  exports: [
    LoginComponent,
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
