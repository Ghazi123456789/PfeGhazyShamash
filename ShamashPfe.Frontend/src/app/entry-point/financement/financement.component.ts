import { Component, OnInit } from '@angular/core';
import { ContratService } from 'src/service/contrat.service';
import { FactureService } from 'src/service/facture.service';
import { UsersService } from 'src/service/users.service';

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
  Users : any[]= []
  facs : any[]= []
   activeCount : any = 0;
   pourcentActive : any = 0
   pourcentInActive : any = 0
   inactiveCount  : any = 0;

   activeCountC : any = 0;
   pourcentActiveC : any = 0
   pourcentInActiveC : any = 0
   inactiveCountC  : any = 0;

   activeCountF : any = 0;
   pourcentActiveF : any = 0
   pourcentInActiveF : any = 0
   inactiveCountF  : any = 0;
  constructor(private contratService: ContratService , private userservice : UsersService , private factureService : FactureService) { }
   countActiveAndInactiveUsers(users: any[]){
    // Initialiser les compteurs

  
    // Parcourir la liste des utilisateurs
    users.forEach(user => {
      if (user.enabled == true) {
       this.activeCount++;
       this.Users.push(user)
      } else {
        this.inactiveCount++;
      }
    });
    this.pourcentActive =Math.round( this.activeCount /this.nbrUser * 100)
    this.pourcentInActive =Math.round( this.inactiveCount /this.nbrUser * 100)
  }

  countContrat(contart: any[]){
    // Initialiser les compteurs
  
    // Parcourir la liste des utilisateurs
    contart.forEach(c => {
      if (c.etat == true) {
       this.activeCountC++;
      } else {
        this.inactiveCountC++;
      }
    });
    this.pourcentActiveC =Math.round( this.activeCountC /this.nbrContrat * 100)
    this.pourcentInActiveC =Math.round( this.inactiveCountC /this.nbrContrat * 100)
  }

  countFact(contart: any[]){
    // Initialiser les compteurs
  
    // Parcourir la liste des utilisateurs
    contart.forEach(c => {
      if (c.approved == 1) {
       this.activeCountF++;
      } else {
        this.inactiveCountF++;
      }
    });
    this.pourcentActiveF =Math.round( this.activeCountF /this.nbrFacture * 100)
    this.pourcentInActiveF =Math.round( this.inactiveCountF /this.nbrFacture * 100)
  }

  displayedData: any[] = [];
  itemsPerPage = 7;
  totalPages!: number;
  currentPage = 1;
  updateDisplayedData() {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    this.displayedData = this.Users.slice(startIndex, endIndex);
  }

  nextPage() {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
      this.updateDisplayedData();
    }
  }

  prevPage() {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.updateDisplayedData();
    }
  }
  ngOnInit() {
   
    this.contratService.getNbrFacture().subscribe(nbr => this.nbrFacture = nbr);
    this.contratService.getNbrContrat().subscribe(nbr => this.nbrContrat = nbr);
    this.contratService.getNbrUser().subscribe(nbr => this.nbrUser = nbr);
    this.contratService.getNbrIndevedu().subscribe(nbr => this.nbrIndevedu = nbr);
    this.userservice.getAllUsers().subscribe(nbr => {this.Users = nbr
      this.countActiveAndInactiveUsers(nbr)
      console.log("aaaaaaaaaaaaaaaaa" , this.Users)
      this.Users =  this.Users.filter(user => user.enabled === true);

      this.totalPages = Math.ceil(this.Users.length / this.itemsPerPage);
      this.updateDisplayedData();}
      );
      this.factureService.getAllFactures().subscribe(nbr => {this.facs = nbr
        this.countFact(nbr)});

    this.contratService.getAllContrats()
      .subscribe(
        response => {
          this.contrats = response;
          console.log('Contrats fetched:', this.contrats);
          this.countContrat(response)
        },
        error => {
          console.error('Error fetching contrats:', error);
          // Handle error, show an error message, etc.
        }
      );
     
  }
  countFactU(contart: any[]){
    // Initialiser les compteurs
  
    // Parcourir la liste des utilisateurs
    contart.forEach(c => {
      if (c.approved == 1) {
       this.activeCountFU++;
      } else {
        this.inactiveCountFU++;
      }
    });
    if(contart.length != 0){
    this.pourcentActiveFU =Math.round( this.activeCountFU /contart.length * 100)
    this.pourcentInActiveFU =Math.round( this.inactiveCountFU /contart.length * 100)
    }
  }
  nbrFF : any = 0
  activeCountFU : any = 0;
  pourcentActiveFU : any = 0
  pourcentInActiveFU : any = 0
  inactiveCountFU  : any = 0;
  
  nbrCC : any = 0
  activeCountCC : any = 0;
  pourcentActiveCC : any = 0
  pourcentInActiveCC : any = 0
  inactiveCountCC  : any = 0;

  getUserAllInfo(idUser : any){
    this.nbrFF = 0;
    this.activeCountFU = 0;
    this.pourcentActiveFU = 0;
    this.pourcentInActiveFU = 0;
    this.inactiveCountFU = 0;

    this.nbrCC = 0;
    this.activeCountCC = 0;
    this.pourcentActiveCC = 0;
    this.pourcentInActiveCC = 0;
    this.inactiveCountCC = 0;


    this.factureService.getFacturesForUser(idUser).subscribe(rep =>{
      this.nbrFF = rep.length
      this.countFactU(rep)
      console.log( rep ,rep.length)

    })

    this.contratService.getAllContratsForUser(idUser).subscribe(rep =>{
      this.nbrCC = rep.length
      this.countConttU(rep)
 

    })

  }



  countConttU(contart: any[]){
    // Initialiser les compteurs
  
    // Parcourir la liste des utilisateurs
    contart.forEach(c => {
      if (c.etat == true) {
       this.activeCountCC++;
      } else {
        this.inactiveCountCC++;
      }
    });
    if(contart.length != 0){
    this.pourcentActiveCC =Math.round( this.activeCountCC /contart.length * 100)
    this.pourcentInActiveCC =Math.round( this.inactiveCountCC /contart.length * 100)
  }
  }

}
