import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-succ-facture',
  templateUrl: './succ-facture.component.html',
  styleUrls: ['./succ-facture.component.css']
})
export class SuccFactureComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }
  navigate(route:any){
    this.router.navigate([route]);

  }
}
