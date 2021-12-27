import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {EventTileOverviewComponent} from './event-tile-overview/event-tile-overview.component';
import {AppRoutingModule} from "../app-routing.module";
import {AppModule} from "../app.module";


@NgModule({
  declarations: [
    EventTileOverviewComponent
  ],
  imports: [
    CommonModule,
    AppRoutingModule,
    AppModule
  ]
})
export class EventTileModule {
}
