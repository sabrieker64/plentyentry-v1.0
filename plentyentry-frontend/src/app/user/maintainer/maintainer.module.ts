import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MaintainerRoutingModule } from './maintainer-routing.module';
import { MaintainedEventsListComponent } from './maintained-events-list/maintained-events-list.component';
import {AngularMaterialModule} from "../../../library/anguler-material-module/anguler-material-module.module";
import { MaintainedEventScanComponent } from './maintained-event-scan/maintained-event-scan.component';


@NgModule({
  declarations: [
    MaintainedEventsListComponent,
    MaintainedEventScanComponent
  ],
  imports: [
    CommonModule,
    MaintainerRoutingModule,
    AngularMaterialModule
  ]
})
export class MaintainerModule { }
