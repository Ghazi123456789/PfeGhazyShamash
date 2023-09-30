import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FactureRoutingModule } from './facture-routing.module';
import { DemandeFactureComponent } from './demande-facture/demande-facture.component';
import { MesFactureComponent } from './mes-facture/mes-facture.component';
import { UsersModule } from '../users/users.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SuccFactureComponent } from './succ-facture/succ-facture.component';
import { AdminDemandeComponent } from './admin-demande/admin-demande.component';


@NgModule({
  declarations: [
    DemandeFactureComponent,
    MesFactureComponent,
    SuccFactureComponent,
    AdminDemandeComponent
  ],
  imports: [
    CommonModule,
    FactureRoutingModule,
    UsersModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class FactureModule { }
