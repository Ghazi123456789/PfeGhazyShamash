import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/service/auth.service';
import { UsersService } from 'src/service/users.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private authService: AuthService,private router: Router,private userService: UsersService) {}
  userId : any = ''
  user: any | undefined;
  role : any =''
  ngOnInit(): void {
    this.userId= localStorage.getItem('id');

    this.userService.getUserById(this.userId).subscribe(
      (user: any) => {
        this.user = user 
        console.log(user)
        this.role = user.roles[0].name
        console.log(this.role)
      },
      (error) => {
        console.error('Error fetching user:', error);
      }
    );
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);  }

    users() {

      this.router.navigate(['/user']);  }
     indv () {

        this.router.navigate(['/user/indevedu']);  }
        profile () {

          this.router.navigate(['/profile']);  }

          navigate (str : any) {
      
            this.router.navigate([str]);  }
}
