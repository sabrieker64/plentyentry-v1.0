import {APP_INITIALIZER, NgModule} from '@angular/core';
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
import {NavigationComponent} from './navigation/navigation.component';
import {NgxSplideModule} from 'ngx-splide';
import {EventTileModule} from "./events/event-tile.module";
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {PaymentCheckoutComponent} from './payment/checkout/payment-checkout.component';
import {ToolBarModule} from "../library/tool-bar/tool-bar.module";
import {ServiceWorkerModule, SwUpdate} from '@angular/service-worker';
import {environment} from '../environments/environment';
import {StripeModule} from "stripe-angular";

export const checkForUpdates = (swUpdate: SwUpdate): (() => Promise<any>) => {
  return (): Promise<void> =>
    new Promise((resolve) => {
      swUpdate.checkForUpdate()

      swUpdate.available.subscribe(() => {
        window.location.reload();
      });

      resolve();
    });
};

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    PaymentCheckoutComponent
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
    EventTileModule,
    FormsModule,
    ReactiveFormsModule,
    ToolBarModule,
    StripeModule.forRoot(environment.stripe),
    ServiceWorkerModule.register('ngsw-worker.js', {
      enabled: environment.production,
      // Register the ServiceWorker as soon as the application is stable
      // or after 30 seconds (whichever comes first).
      registrationStrategy: 'registerWhenStable:30000'
    }),
  ],
  providers: [AuthService, {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},
    {provide: APP_INITIALIZER, useFactory: checkForUpdates, deps:[SwUpdate], multi: true}],
  bootstrap: [AppComponent],
})
export class AppModule {
}
