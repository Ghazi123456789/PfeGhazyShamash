import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListUsersComponent } from './list-users/list-users.component';
import { DetailsUserComponent } from './details-user/details-user.component';
import { IndeveduComponent } from './indevedu/indevedu.component';

const routes: Routes = [
  {path:'',
component: ListUsersComponent },
{path:'detail/:id',
component: DetailsUserComponent },
{path:'indevedu',
component: IndeveduComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsersRoutingModule { }
