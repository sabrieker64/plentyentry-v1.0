import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PlentyentryHomeComponent} from './directory/plentyentry-home.component';


@NgModule({
  declarations: [
    PlentyentryHomeComponent
  ],
  exports: [
    PlentyentryHomeComponent
  ],
  imports: [
    CommonModule
  ]
})
export class PlentyentryHomeModule {
}
