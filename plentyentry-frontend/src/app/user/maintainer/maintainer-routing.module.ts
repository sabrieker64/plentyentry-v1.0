import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MaintainedEventsListComponent} from "./maintained-events-list/maintained-events-list.component";
import {MaintainedEventEditComponent} from "./maintained-event-edit/maintained-event-edit.component";
import {MaintainedEventCreateComponent} from "./maintained-event-create/maintained-event-create.component";
import {MaintainedEventScanComponent} from "./maintained-event-scan/maintained-event-scan.component";

const routes: Routes = [

  {
    path: 'maintained/events/list',
    component: MaintainedEventsListComponent
  },
  {
    path: 'maintained/events/edit/:id',
    component: MaintainedEventEditComponent,
  },
  {
    path: 'maintained/events/scan',
    component: MaintainedEventScanComponent
  },
  {
    path: 'maintained/events/create',
    component: MaintainedEventCreateComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MaintainerRoutingModule { }
