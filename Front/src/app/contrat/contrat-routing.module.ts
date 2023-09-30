import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddContratComponent } from './add-contrat/add-contrat.component';
import { AdminContratsComponent } from './admin-contrats/admin-contrats.component';
import { MesContratComponent } from './mes-contrat/mes-contrat.component';

const routes: Routes = [
  {path : 'addContart/:id' , component : AddContratComponent},
  {path : 'MesContract' , component : MesContratComponent},
  {path : 'Contract' , component : AdminContratsComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContratRoutingModule { }
