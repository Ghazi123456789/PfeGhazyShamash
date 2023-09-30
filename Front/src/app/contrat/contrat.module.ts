import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ContratRoutingModule } from './contrat-routing.module';
import { AddContratComponent } from './add-contrat/add-contrat.component';
import { AdminContratsComponent } from './admin-contrats/admin-contrats.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MesContratComponent } from './mes-contrat/mes-contrat.component';
import { ContratFichierComponent } from './contrat-fichier/contrat-fichier.component';


@NgModule({
  declarations: [
    AddContratComponent,
    AdminContratsComponent,
    MesContratComponent,
    ContratFichierComponent
  ],
  imports: [
    CommonModule,
    ContratRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class ContratModule { }
