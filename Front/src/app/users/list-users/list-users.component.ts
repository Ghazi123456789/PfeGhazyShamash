import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UsersService } from 'src/service/users.service';

@Component({
  selector: 'app-list-users',
  templateUrl: './list-users.component.html',
  styleUrls: ['./list-users.component.css']
})
export class ListUsersComponent implements OnInit {
  users: any[] = [];

  constructor(private userService: UsersService,private router: Router) {}


  ngOnInit() {
this.getAll()
  }
getAll(){
  this.userService.getAllUsers().subscribe(
    users => {
      this.users = users;
      console.log(users)
    },
    error => {
      console.error('Error fetching users:', error);
    }
  );
}

  openuserDetails(id: string): void {
    this.router.navigate(['user/detail', id]);
  }

  user:any = ""

  userdetails(id:any){
    this.userService.getUserById(id).subscribe(
      (user: any) => {
        this.user = user;
        console.log(this.user)
      },
      (error) => {
        console.error('Error fetching user:', error);
      }
    );
  }

  activeUser(){
    this.userService.activeUser(this.user.id).subscribe(
      (user: any) => {
      window.location.reload()
      },
      (error) => {
        console.error('Error fetching user:', error);
      }
    );
  }
  deleteMessage: string = '';
  deleteUser(userId :any) {
    this.userService.deleteUser(userId).subscribe(
      (response: any) => {
        this.getAll()

        this.deleteMessage = response.message;
      },
      (error) => {
        console.error('Error deleting user:', error);
      }
    );
  }
}
