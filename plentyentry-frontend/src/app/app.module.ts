import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-routing.module";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {NavigationLayoutModule} from "../library/navigation-layout/navigation-layout.module";
import {EventTileModule} from "./event-tile/event-tile.module";
import {FooterLayoutModule} from "../library/footer-layout/footer-layout.module";
import {AngularMaterialModule} from "../library/anguler-material-module/anguler-material-module.module";
import {RouterModule} from "@angular/router";


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    NavigationLayoutModule,
    AngularMaterialModule,
    AppRoutingModule,
    EventTileModule,
    FooterLayoutModule,
    RouterModule.forRoot([])
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {
}
