import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UserListComponent} from "./user-list/user-list.component";
import {UserEditComponent} from "./user-edit/user-edit.component";

const routes: Routes = [
  {
    path: 'user/list',
    component: UserListComponent
  },
  {
    path: 'user/edit/:id',
    component: UserEditComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SpecialPrivilegesRoutingModule {
}
