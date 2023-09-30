import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Subscription } from 'rxjs';
import { Contart } from 'src/modal/contrat.model';
import { ContratService } from 'src/service/contrat.service';

@Component({
  selector: 'app-add-contrat',
  templateUrl: './add-contrat.component.html',
  styleUrls: ['./add-contrat.component.css']
})
export class AddContratComponent implements OnInit {

 
contrat : Contart = new Contart(); // Modify this type based on your Contrat model
routeSub!: Subscription;
id : any

  constructor(private contratService: ContratService,private activatedRoute: ActivatedRoute) { }

  updateContrat() {
    this.contratService.updateContrat(this.id, this.contrat)
      .subscribe(
        response => {
          console.log('Contrat updated successfully:', response);
          // Handle success, show a message, update UI, etc.
        },
        error => {
          console.error('Error updating contrat:', error);
          // Handle error, show an error message, etc.
        }
      );
  }

  getOne() {
    this.contratService.getContratById(this.id)
      .subscribe(
        response => {
          this.contrat = response;
          console.log('Contrat fetched:', this.contrat);
          // Handle success, update UI, etc.
        },
        error => {
          console.error('Error fetching contrat:', error);
          // Handle error, show an error message, etc.
        }
      );
  }
  ngOnInit(): void {
    this.routeSub = this.activatedRoute.params.subscribe((params: Params) => {
this.id = params['id']
     } )
     this.getOne();

}
}
