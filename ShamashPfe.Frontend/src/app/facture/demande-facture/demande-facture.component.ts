import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Facture } from 'src/modal/facture.model';
import { FactureService } from 'src/service/facture.service';

@Component({
  selector: 'app-demande-facture',
  templateUrl: './demande-facture.component.html',
  styleUrls: ['./demande-facture.component.css']
})
export class DemandeFactureComponent implements OnInit {
  facture : Facture = new Facture();
  userId: any | null = null;

  constructor(private factureService: FactureService,private router: Router) { }

  sh1 : boolean = false;
  showdang(){
    this.sh1 = ! this.sh1
  }
  selectedFiles: File[] = []; // Array to hold selected files


  onFileSelect(event: any): void {
    this.selectedFiles = event.target.files;
  }

  onSubmit() {
    this.factureService.addDemande(this.userId, this.facture ,this.selectedFiles)
      .subscribe(
        response => {
          console.log('Demande added successfully:', response);
          this.router.navigate(['facture/succ']);

        },
        error => {
          if(error.status == 0 || error.status == 200) {this.router.navigate(['facture/succ']);
        }
          console.error('Error adding demande:', error);
          this.showdang()
        }
      );
  }
  ngOnInit(): void {
    this.userId= localStorage.getItem('id');
  }

}
