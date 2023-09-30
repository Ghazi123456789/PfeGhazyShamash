import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import jwt_decode from 'jwt-decode';
import { indevedu } from 'src/modal/indevedu.model';
import { UsersService } from 'src/service/users.service';
@Component({
  selector: 'app-indevedu',
  templateUrl: './indevedu.component.html',
  styleUrls: ['./indevedu.component.css']
})
export class IndeveduComponent implements OnInit {
  @Input() but : boolean = false;
  @Input() id : number = 0;
  constructor(private userService: UsersService,private activatedRoute: ActivatedRoute) { }

  userId: any | null = null;
  user : any | null = null;
  indev: indevedu = new indevedu()
  ngOnInit(): void {
    this.userId= localStorage.getItem('id');
    this.getindevedu(this.userId)
    this.getuser(this.userId)
  }
 
    getindevedu(id :any){
      if(this.id !== 0){
        this.userService.getIndeveduById(this.id).subscribe(
          (rep: any) => {
          console.log(rep)
          this.indev=rep
          },
          (error) => {
            console.error('Error fetching user:', error);
          }
        );
      }else{
    
          this.userService.getIndeveduById(id).subscribe(
            (rep: any) => {
            console.log(rep)
            this.indev=rep
            },
            (error) => {
              console.error('Error fetching user:', error);
            }
          );
   
    }}
    getuser(id :any){
      this.userService.getUserById(id).subscribe(
        (rep: any) => {
        console.log(rep)
        this.user=rep
        },
        (error) => {
          console.error('Error fetching user:', error);
        }
      );
    }
    updateMessage: string = '';
    updateIndevedu() {
      console.log(this.indev)
      this.userService.updateFicheIndevedu(this.userId, this.indev).subscribe(
        (response: any) => {
          this.updateMessage = response;
          this.showsucc()
        },
        (error) => {
          if(error.status === 200)
          {
            this.showsucc()
          }
          if(error.status !== 200){
          console.error('Error updating Indevedu:', error);

          this.showdang()
       
          }
      }
      );
    }

    sh : boolean = false;
    showsucc(){
      this.sh = ! this.sh
    }
    msg : string =""
    sh1 : boolean = false;
    showdang(){
      this.sh1 = ! this.sh1
    }
  
  

}
