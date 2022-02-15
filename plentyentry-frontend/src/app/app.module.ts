import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-routing.module";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {NavigationLayoutModule} from "../library/navigation-layout/navigation-layout.module";
import {FooterLayoutModule} from "../library/footer-layout/footer-layout.module";
import {AngularMaterialModule} from "../library/anguler-material-module/anguler-material-module.module";
import {RouterModule} from "@angular/router";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AuthService} from "./auth/auth.service";
import {AuthInterceptor} from "./auth/auth.interceptor";
import { NavigationComponent } from './navigation/navigation.component';
import {NgxSplideModule} from 'ngx-splide';
import {EventTileModule} from "./events/event-tile.module";
import {SetActiveClassDirective} from "../library/directives/setActiveClass.directive";

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    SetActiveClassDirective
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    NavigationLayoutModule,
    AngularMaterialModule,
    FooterLayoutModule,
    HttpClientModule,
    RouterModule.forRoot([]),
    AppRoutingModule,
    NgxSplideModule,
    EventTileModule
  ],
  providers: [AuthService, {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}],
  bootstrap: [AppComponent],
})
export class AppModule {
}
