import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-routing.module";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {NavigationLayoutModule} from "../library/navigation-layout/navigation-layout.module";
import {EventTileModule} from "./event-tile/event-tile.module";
import {FooterLayoutModule} from "../library/footer-layout/footer-layout.module";


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    NavigationLayoutModule,
    AppRoutingModule,
    EventTileModule,
    FooterLayoutModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {
}
