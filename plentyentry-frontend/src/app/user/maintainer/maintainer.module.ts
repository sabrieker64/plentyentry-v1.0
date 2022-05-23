import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {MaintainerRoutingModule} from './maintainer-routing.module';
import {MaintainedEventsListComponent} from './maintained-events-list/maintained-events-list.component';
import {AngularMaterialModule} from "../../../library/anguler-material-module/anguler-material-module.module";
import {MaintainedEventEditComponent} from './maintained-event-edit/maintained-event-edit.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MaintainedEventCreateComponent} from './maintained-event-create/maintained-event-create.component';
import {MaintainedEventScanComponent} from "./maintained-event-scan/maintained-event-scan.component";
import {BecomeAMaintainerComponent} from './become-amaintainer/become-amaintainer.component';


@NgModule({
  declarations: [
    MaintainedEventsListComponent,
    MaintainedEventScanComponent,
    MaintainedEventEditComponent,
    MaintainedEventCreateComponent,
    BecomeAMaintainerComponent
  ],
  imports: [
    CommonModule,
    MaintainerRoutingModule,
    AngularMaterialModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class MaintainerModule { }
