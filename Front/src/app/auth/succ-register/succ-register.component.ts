import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-succ-register',
  templateUrl: './succ-register.component.html',
  styleUrls: ['./succ-register.component.css']
})
export class SuccRegisterComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }
gologin(){
  this.router.navigate(['login']);
}
}
