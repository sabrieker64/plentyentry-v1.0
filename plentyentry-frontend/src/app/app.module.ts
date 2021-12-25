import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {RouterModule, Routes} from "@angular/router";

const routes: Routes = [

  {
    path: ' ',
    redirectTo: 'home',
    pathMatch: 'full'
  }
]

@NgModule({
  exports: [RouterModule],
  declarations: [
    AppComponent
  ],
  imports: [
    RouterModule.forChild(routes),
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
