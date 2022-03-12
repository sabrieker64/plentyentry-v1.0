import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PlentyentryHomeComponent} from "../library/plentyentry-home/directory/plentyentry-home.component";

const routes: Routes = [
  // {path: '**', redirectTo: 'event', pathMatch: 'full'},
  {path: 'home', component: PlentyentryHomeComponent},

  {
    path: 'event',
    loadChildren: () => import('./events/event-tile.module').then(m => m.EventTileModule)
  },
  {
    path: 'user',
    loadChildren: () => import('./user/user-register.module').then(m => m.UserRegisterModule)
  },
  {
    path: 'payment',
    loadChildren: () => import('./payment/payment-checkout.module').then(m => m.PaymentCheckoutModule)
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
