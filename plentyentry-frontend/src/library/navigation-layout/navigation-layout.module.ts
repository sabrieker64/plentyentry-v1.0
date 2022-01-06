import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NavigationLayoutComponent} from "./layout/navigation-layout.component";
import {AngularMaterialModule} from "../anguler-material-module/anguler-material-module.module";
import {AppRoutingModule} from "../../app/app-routing.module";


@NgModule({
  declarations: [
    NavigationLayoutComponent
  ],
  imports: [
    CommonModule,
    AngularMaterialModule,
    AppRoutingModule,
  ],
  providers: [],
  exports: [
    NavigationLayoutComponent
  ]
})
export class NavigationLayoutModule {
}
