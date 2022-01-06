import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PlentyentryHomeComponent} from "../library/plentyentry-home/directory/plentyentry-home.component";

const routes: Routes = [
  {path: 'home', component: PlentyentryHomeComponent, pathMatch: 'full'},

  {path: '**', redirectTo: 'event', pathMatch: 'full'},

  {
    path: 'event',
    loadChildren: () => import('./events/event-tile.module').then(m => m.EventTileModule)
  },
  {
    path: 'user',
    loadChildren: () => import('./user/login-register.module').then(m => m.LoginRegisterModule)
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
