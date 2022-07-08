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

function initializeApp(): Promise<any> {
  return new Promise(async (resolve, reject) => {
    try {
      // Check if Service Worker is supported by the Browser
      if (this.swUpdate.isEnabled) {
        const isNewVersion = await this.swUpdate.checkForUpdate();
        // Check if the new version is available
        if (isNewVersion) {
          const isNewVersionActivated = await this.swUpdate.activateUpdate();
          // Check if the new version is activated and reload the app if it is
          if (isNewVersionActivated) window.location.reload();
          resolve(true);
        }
        resolve(true);
      }
      resolve(true);
    } catch (error) {
      window.location.reload();
    }
  });
}

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
      registrationStrategy: 'registerImmediately'
    }),
  ],
  providers: [AuthService, {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},
    {provide: APP_INITIALIZER, useFactory: initializeApp, deps:[SwUpdate], multi: true}],
  bootstrap: [AppComponent],
})
export class AppModule {
}
