import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: string = '';
  password: string = '';

  errorMessage: string = '';

constructor(private authService: AuthService ,     private router: Router
  ) {}
  ngOnInit(): void {
  }

  goRegister(){
    this.router.navigate(['login/register']);
  }

  login() {
    const loginRequest = { username: this.username, password: this.password };
    console.log(loginRequest)
    this.authService.authenticateUser(loginRequest).subscribe(
      response => {
        const token = response.accessToken; // Modify this based on your response structure
        
       
        localStorage.setItem('token', token);
        localStorage.setItem('id', response.id);
        
                // Redirect to the dashboard page
                this.router.navigate(['/']);      },
        
error => {
  this.errorMessage = 'Invalid username or password.';
}
    );
  }



}

