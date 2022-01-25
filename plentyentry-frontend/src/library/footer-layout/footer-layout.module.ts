import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FooterLayoutComponent} from './footer-layout/footer-layout.component';
import {AngularMaterialModule} from "../anguler-material-module/anguler-material-module.module";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    FooterLayoutComponent
  ],
  exports: [
    FooterLayoutComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    AngularMaterialModule

  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FooterLayoutModule {
}
