import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {EventTileRoutingModule} from './event-tile-routing.module';
import {EventTileOverviewComponent} from './event-tile-overview/event-tile-overview.component';


@NgModule({
  declarations: [
    EventTileOverviewComponent
  ],
  imports: [
    CommonModule,
    EventTileRoutingModule
  ]
})
export class EventTileModule {
}
