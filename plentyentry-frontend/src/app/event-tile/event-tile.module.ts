import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {EventTileOverviewComponent} from './event-tile-overview/event-tile-overview.component';
import {AppRoutingModule} from "../app-routing.module";
import {AngularMaterialModule} from "../../library/anguler-material-module/anguler-material-module.module";


@NgModule({
  declarations: [
    EventTileOverviewComponent
  ],
  exports: [
    EventTileOverviewComponent
  ],
  imports: [
    CommonModule,
    AngularMaterialModule,
    AppRoutingModule
  ]
})
export class EventTileModule {
}
