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
  },
  {
    path: 'ticket',
    loadChildren: () => import('./ticket/ticket.module').then(m => m.TicketModule)
  },
  {
    path: 'maintainedevents',
    loadChildren: () => import('./user/maintainer/maintainer.module').then(m => m.MaintainerModule)
  },
  {
    path: 'shoppingcart',
    loadChildren: () => import('./user/shoppingcart/shoppingcart.module').then(m => m.ShoppingcartModule)
  },
  {
    path: 'toolbar',
    loadChildren: () => import('../library/tool-bar/tool-bar.module').then(m => m.ToolBarModule)
  },
  {
    path: 'special-privileges',
    loadChildren: () => import('./user/special-privileges/special-privileges.module').then(m => m.SpecialPrivilegesModule)
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
