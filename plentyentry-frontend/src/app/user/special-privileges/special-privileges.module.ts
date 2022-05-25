import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {SpecialPrivilegesRoutingModule} from './special-privileges-routing.module';
import {UserListComponent} from './user-list/user-list.component';
import {MatTableModule} from "@angular/material/table";
import {MatIconModule} from "@angular/material/icon";
import {MatFormFieldModule} from "@angular/material/form-field";
import {AngularMaterialModule} from "../../../library/anguler-material-module/anguler-material-module.module";


@NgModule({
  declarations: [
    UserListComponent
  ],
  imports: [
    CommonModule,
    SpecialPrivilegesRoutingModule,
    AngularMaterialModule,
    MatTableModule,
    MatIconModule,
    MatFormFieldModule
  ]
})
export class SpecialPrivilegesModule {
}
