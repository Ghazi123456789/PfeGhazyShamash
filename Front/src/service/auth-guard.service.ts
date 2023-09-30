import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService {

  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): boolean {
      
     
  if (this.authService.isLoggedIn()) { // Assuming you have a method to check token presence in AuthService
        return true;
      } 

  else {
        this.router.navigate(['/login']); // Redirect to login if not logged in
        return false;
      }
    }
  }
