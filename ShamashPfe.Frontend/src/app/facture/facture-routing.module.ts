import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DemandeFactureComponent } from './demande-facture/demande-facture.component';
import { MesFactureComponent } from './mes-facture/mes-facture.component';
import { SuccFactureComponent } from './succ-facture/succ-facture.component';
import { AdminDemandeComponent } from './admin-demande/admin-demande.component';

const routes: Routes = [
  {path: 'demande' , component : DemandeFactureComponent},
  {path: '' ,component : MesFactureComponent},
  {path: 'succ' ,component : SuccFactureComponent},
  {path: 'demandeUser' ,component : AdminDemandeComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FactureRoutingModule { }
