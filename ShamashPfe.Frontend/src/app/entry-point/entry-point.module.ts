import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EntryPointRoutingModule } from './entry-point-routing.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { UsersModule } from '../users/users.module';
import { FinancementComponent } from './financement/financement.component';
import { HomeComponent } from './home/home.component';


@NgModule({
  declarations: [
    DashboardComponent,
    UserProfileComponent,
    FinancementComponent,
    HomeComponent
  ],
  imports: [
    CommonModule,
    EntryPointRoutingModule,
    UsersModule,
    
  ]
})
export class EntryPointModule { }
