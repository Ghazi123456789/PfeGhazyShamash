import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Subscription } from 'rxjs';
import { UsersService } from 'src/service/users.service';

@Component({
  selector: 'app-details-user',
  templateUrl: './details-user.component.html',
  styleUrls: ['./details-user.component.css']
})
export class DetailsUserComponent implements OnInit {
  userId :any;
  user: any | undefined;
  routeSub!: Subscription;

  constructor(private userService: UsersService,private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.routeSub = this.activatedRoute.params.subscribe((params: Params) => {
      this.userId = params['id'];
    
    });
    this.userService.getUserById(this.userId).subscribe(
      (user: any) => {
        this.user = user;
        console.log(this.user)
      },
      (error) => {
        console.error('Error fetching user:', error);
      }
    );
  }



  }


