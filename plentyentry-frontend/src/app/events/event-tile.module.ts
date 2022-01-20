import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AppRoutingModule} from "../app-routing.module";
import {AngularMaterialModule} from "../../library/anguler-material-module/anguler-material-module.module";
import {EventTileRoutingModule} from "./event-tile-routing.module";
import {EventTileOverviewComponent} from "./event-tile-overview/event-tile-overview.component";
import {FormsModule} from "@angular/forms";


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
        EventTileRoutingModule,
        AppRoutingModule,
        FormsModule
    ]
})
export class EventTileModule {
}
