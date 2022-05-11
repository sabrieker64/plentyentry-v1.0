import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MaintainedEventsListComponent} from "./maintained-events-list/maintained-events-list.component";
import {MaintainedEventScanComponent} from "./maintained-event-scan/maintained-event-scan.component";

const routes: Routes = [

  {
    path: 'maintained/events/list',
    component: MaintainedEventsListComponent
  },
  {
    path: 'maintained/events/scan',
    component: MaintainedEventScanComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MaintainerRoutingModule { }
