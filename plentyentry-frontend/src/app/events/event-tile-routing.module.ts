import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {EventTileOverviewComponent} from "./event-tile-overview/event-tile-overview.component";
import {EventDetailComponent} from "./event-detail/event-detail.component";

const routes: Routes = [

  {
    path: '',
    redirectTo: 'overview',
    pathMatch: 'full',
  },
  {
    path: 'overview',
    component: EventTileOverviewComponent
  },
  {
    path: ':id',
    component: EventDetailComponent
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EventTileRoutingModule {
}
