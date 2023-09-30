import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuardService } from 'src/service/auth-guard.service';

const routes: Routes = [
  {
    path : "login",
    loadChildren: () => import ('./auth/auth.module').then(m => m.AuthModule )
  },
  {
    path : "",
    loadChildren: () => import ('./entry-point/entry-point.module').then(m => m.EntryPointModule ) , canActivate: [AuthGuardService]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
