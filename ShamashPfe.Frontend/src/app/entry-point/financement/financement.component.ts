import { Component, OnInit } from '@angular/core';
import { ContratService } from 'src/service/contrat.service';

@Component({
  selector: 'app-financement',
  templateUrl: './financement.component.html',
  styleUrls: ['./financement.component.css']
})
export class FinancementComponent implements OnInit {

  contrats: any[] = []; // Modify this type based on your Contrat model
  nbrFacture: number = 0;
  nbrContrat: number = 0;
  nbrUser: number = 0;
  nbrIndevedu: number = 0;
  constructor(private contratService: ContratService) { }

  ngOnInit() {
    this.contratService.getNbrFacture().subscribe(nbr => this.nbrFacture = nbr);
    this.contratService.getNbrContrat().subscribe(nbr => this.nbrContrat = nbr);
    this.contratService.getNbrUser().subscribe(nbr => this.nbrUser = nbr);
    this.contratService.getNbrIndevedu().subscribe(nbr => this.nbrIndevedu = nbr);

    this.contratService.getAllContrats()
      .subscribe(
        response => {
          this.contrats = response;
          console.log('Contrats fetched:', this.contrats);
          // Handle success, update UI, etc.
        },
        error => {
          console.error('Error fetching contrats:', error);
          // Handle error, show an error message, etc.
        }
      );
  }

}
