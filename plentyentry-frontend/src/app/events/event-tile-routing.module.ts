import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {EventTileOverviewComponent} from "./event-tile-overview/event-tile-overview.component";

const routes: Routes = [

  {
    path: '',
    redirectTo: 'overview',
    pathMatch: 'full',
  },

  {
    path: 'overview',
    component: EventTileOverviewComponent,
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EventTileRoutingModule {
}
