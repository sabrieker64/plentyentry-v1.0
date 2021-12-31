import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FooterLayoutComponent} from './footer-layout/footer-layout.component';
import {MatIconModule} from "@angular/material/icon";


@NgModule({
  declarations: [
    FooterLayoutComponent
  ],
  exports: [
    FooterLayoutComponent
  ],
  imports: [
    CommonModule,
    MatIconModule
  ]
})
export class FooterLayoutModule {
}
