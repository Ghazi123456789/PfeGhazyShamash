import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/service/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
    errorMessage: string = '';

  constructor(private authService: AuthService, private fb: FormBuilder,private router: Router) {
    this.registerForm = this.fb.group({
      name: ['', Validators.required],
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      phone: ['', [Validators.required, Validators.minLength(6),Validators.maxLength(8)]],
      cin: ['', [Validators.required, Validators.minLength(8),Validators.maxLength(8)]],
      description: ['', [Validators.required]],
      
    });
  }

  ngOnInit(): void {
  }

  onSubmit() {
    if (this.registerForm.valid) {
      const signUpData = this.registerForm.value;
      this.authService.registerUser(signUpData).subscribe(
        response => {
          this.router.navigate(['login/SuccRegister']);
        },
        error => {
          this.errorMessage = error.error.message;
        }
      );
    }
  }

}
