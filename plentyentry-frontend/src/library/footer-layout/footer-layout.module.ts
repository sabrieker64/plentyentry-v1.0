import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FooterLayoutComponent} from './footer-layout/footer-layout.component';
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {AngularMaterialModule} from "../anguler-material-module/anguler-material-module.module";
import {MatSliderModule} from "@angular/material/slider";
import {FlexLayoutModule} from "@angular/flex-layout";

@NgModule({
  declarations: [
    FooterLayoutComponent
  ],
  exports: [
    FooterLayoutComponent
  ],
  imports: [
    CommonModule,
    MatIconModule,
    AngularMaterialModule,
    MatButtonModule,
    MatSliderModule,
    FlexLayoutModule
  ]
})
export class FooterLayoutModule {
}
