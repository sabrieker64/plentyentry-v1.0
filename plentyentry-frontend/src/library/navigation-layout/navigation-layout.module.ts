import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NavigationLayoutComponent} from "./layout/navigation-layout.component";
import {AngularMaterialModule} from "../anguler-material-module/anguler-material-module.module";
import {RouterModule} from "@angular/router";


@NgModule({
  declarations: [
    NavigationLayoutComponent
  ],
  imports: [
    CommonModule,
    AngularMaterialModule,
    RouterModule
  ],
  providers: [],
  exports: [
    NavigationLayoutComponent
  ]
})
export class NavigationLayoutModule {
}
