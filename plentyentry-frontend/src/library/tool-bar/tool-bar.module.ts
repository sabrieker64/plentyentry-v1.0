import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AngularMaterialModule} from "../anguler-material-module/anguler-material-module.module";
import {AppRoutingModule} from "../../app/app-routing.module";
import {ToolBarComponent} from './tool-bar/tool-bar.component';

@NgModule({
  declarations: [
    ToolBarComponent
  ],
  imports: [
    CommonModule,
    AngularMaterialModule,
    AppRoutingModule,
  ],
  providers: [],
  exports: [
    ToolBarComponent
  ]
})
export class ToolBarModule {
}
