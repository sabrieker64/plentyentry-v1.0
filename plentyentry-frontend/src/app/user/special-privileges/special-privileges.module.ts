import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {SpecialPrivilegesRoutingModule} from './special-privileges-routing.module';
import {UserListComponent} from './user-list/user-list.component';
import {MatTableModule} from "@angular/material/table";
import {MatIconModule} from "@angular/material/icon";
import {MatFormFieldModule} from "@angular/material/form-field";
import {AngularMaterialModule} from "../../../library/anguler-material-module/anguler-material-module.module";
import {UserEditComponent} from './user-edit/user-edit.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    UserListComponent,
    UserEditComponent
  ],
  imports: [
    CommonModule,
    SpecialPrivilegesRoutingModule,
    AngularMaterialModule,
    MatTableModule,
    MatIconModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class SpecialPrivilegesModule {
}
