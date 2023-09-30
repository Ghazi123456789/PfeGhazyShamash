import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsersRoutingModule } from './users-routing.module';
import { ListUsersComponent } from './list-users/list-users.component';
import { DetailsUserComponent } from './details-user/details-user.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { IndeveduComponent } from './indevedu/indevedu.component';


@NgModule({
  declarations: [
    ListUsersComponent,
    DetailsUserComponent,
    IndeveduComponent
  ],
  imports: [
    CommonModule,
    UsersRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  exports: [
    IndeveduComponent
  ]
})
export class UsersModule { }
