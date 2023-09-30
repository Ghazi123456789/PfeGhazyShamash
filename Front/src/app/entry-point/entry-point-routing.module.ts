import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AuthGuardService } from 'src/service/auth-guard.service';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { FinancementComponent } from './financement/financement.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  {path:'',
component: DashboardComponent,
children : [
  {path:'profile',
  component: UserProfileComponent },
  {path:'',
  component: HomeComponent },
  {path:'financement',
  component: FinancementComponent },
  {
    path : "user",
    loadChildren: () => import ('../users/users.module').then(m => m.UsersModule )
  },
  {
    path : "facture",
    loadChildren: () => import ('../facture/facture.module').then(m => m.FactureModule )
  },
  {
    path : "contrat",
    loadChildren: () => import ('../contrat/contrat.module').then(m => m.ContratModule )
  },
]

}



];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EntryPointRoutingModule { }
