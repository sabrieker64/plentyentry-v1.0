import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FooterLayoutComponent} from './footer-layout/footer-layout.component';
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";


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
    MatButtonModule
  ]
})
export class FooterLayoutModule {
}
