import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Facture } from 'src/modal/facture.model';
import { FactureService } from 'src/service/facture.service';

@Component({
  selector: 'app-admin-demande',
  templateUrl: './admin-demande.component.html',
  styleUrls: ['./admin-demande.component.css']
})
export class AdminDemandeComponent implements OnInit {
  factures: Facture[] = [];
  userId: any | null = null;

  constructor(private factureService: FactureService,private router: Router) { }

  ngOnInit() {
    this.userId= localStorage.getItem('id');
    this.factureService.getAllFactures()
    .subscribe(
      response => {
        this.factures = response;
        console.log('Factures fetched:', this.factures);
        // Handle success, update UI, etc.
      },
      error => {
        console.error('Error fetching factures:', error);
        // Handle error, show an error message, etc.
      }
    );
  }
  facture: Facture = new Facture();
  idFacture : any =""
  getfacture(id:any){
    this.factureService.getFactureForUser(id)
      .subscribe(
        response => {
          this.facture = response;
          this.loadFiles(id);

          console.log('Facture fetched:', this.facture);
   
        },
        error => {
          console.error('Error fetching facture:', error);
        
        }
      );
  }

  updateFactureState(idfact : any ,newState : any) {
    this.factureService.updateFactureState(idfact,newState)
      .subscribe(
        response => {
          console.log('Facture state updated:', response);
          this.ngOnInit()
        },
        error => {
          console.error('Error updating facture state:', error);
          // Handle error, show an error message, etc.
        }
      );
  }

  files: any[] = []
  loadFiles(id : any): void {
    this.factureService.getFactureFiles(id).subscribe((files) => {
      this.files = files;
      console.log("aaaaaa" ,files)
    });
  }
  navigate (str : any) {
    this.router.navigate(['/contrat/addContart/'+str]);  }
}