import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PlentyentryHomeComponent} from './directory/plentyentry-home.component';
import {EventTileModule} from "../../app/events/event-tile.module";


@NgModule({
  declarations: [
    PlentyentryHomeComponent
  ],
  imports: [
    CommonModule,
    EventTileModule
  ]
})
export class PlentyentryHomeModule {
}
