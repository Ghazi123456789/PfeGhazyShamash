import { Component, OnInit } from '@angular/core';
import { Contart } from 'src/modal/contrat.model';
import { ContratService } from 'src/service/contrat.service';

@Component({
  selector: 'app-mes-contrat',
  templateUrl: './mes-contrat.component.html',
  styleUrls: ['./mes-contrat.component.css']
})
export class MesContratComponent implements OnInit {

  contrats: any[] = []; // Modify this type based on your Contrat model
  userId: any | null = null;
  c : Contart = new Contart(); // Modify this type based on your Contrat model


  constructor(private contratService: ContratService) { }

  ngOnInit() {
    this.userId= localStorage.getItem('id');
    this.contratService.getAllContratsForUser(this.userId)
      .subscribe(
        response => {
          this.contrats = response;
          console.log('Contrats fetched:', this.contrats);
        },
        error => {
          console.error('Error fetching contrats:', error);
        }
      );
  }


  changeContratState(idContrat :any,newEtat: any) {
    this.contratService.changeContratState(idContrat, newEtat)
      .subscribe(
        response => {
          console.log('Contrat state changed:', response);
          this.ngOnInit()
        },
        error => {
          console.error('Error changing contrat state:', error);
          // Handle error, show an error message, etc.
        }
      );
  }
  getOne(id:any) {
   
          this.c = id;
          console.log( this.c);
 
  }
  printSpecificDiv() {
    const printableDiv = document.getElementById('specific-div');
    if (printableDiv) {
      const printWindow = window.open('', '_blank');
      if (printWindow) {
        printWindow.document.write(printableDiv.innerHTML);
        printWindow.print();
      } else {
        console.error('Failed to open print window.');
      }
    }
  }



  
}
