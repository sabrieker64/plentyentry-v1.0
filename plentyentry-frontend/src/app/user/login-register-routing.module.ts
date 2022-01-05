import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {CartComponent} from "./cart/cart.component";
import {UserDetailComponent} from "./user-detail/user-detail.component";

const routes: Routes = [

  {path: 'register', component: RegisterComponent},
  {path: 'login', component: LoginComponent},
  {path: ':id/cart', component: CartComponent},
  {path: ':id/detail', component: UserDetailComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LoginRegisterRoutingModule {
}
