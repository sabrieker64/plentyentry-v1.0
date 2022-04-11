import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {EventTileOverviewComponent} from "./event-tile-overview/event-tile-overview.component";
import {EventDetailComponent} from "./event-detail/event-detail.component";
import {EventCreateComponent} from "./event-create/event-create.component";
import {EventUpdateComponent} from "./event-update/event-update.component";

const routes: Routes = [

  {
    path: '',
    redirectTo: 'event/overview',
    pathMatch: 'full',
  },
  {
    path: 'overview',
    component: EventTileOverviewComponent
  },
  {
    path: 'event-detail/:id',
    component: EventDetailComponent
  },
  {
    path: ':id/update',
    component: EventUpdateComponent
  },
  {
    path: 'event-create',
    component: EventCreateComponent
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EventTileRoutingModule {
}
