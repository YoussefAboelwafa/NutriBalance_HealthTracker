import { Component,OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserService } from '../_services/user.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { User } from '../Objects/User';
import { Coach } from '../Objects/Coach';
import { CoachService } from '../Service/coach.service';

@Component({
  selector: 'app-user-report',
  templateUrl: './user-report.component.html',
  styleUrls: ['./user-report.component.css']
})
export class UserReportComponent implements OnInit {
coaches: Coach[] = [];
 description:any
 user! :User;
 reports :string[]=[];
 loading! :boolean
  constructor(private userservice: UserService,private coachservice:CoachService, private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    this.user = this.tokenStorage.getUser();
    this.coachservice.getCoaches().subscribe(
      (data) => {
        this.coaches = data;
        console.log(this.coaches);
      },
      (error) => {
        console.log(error);
      }
    );
    this.loading=false;
  }
  isdisabled(i:number){
    return   this.reports[i]==undefined ||  /^\s*$/.test(this.reports[i])
  }
report(i:number){
  this.loading=true;
  this.userservice.addReport(this.user.user_id,this.coaches[i].coach_id,this.reports[i]).subscribe({
    next: (response: any) => {
     
      this.loading=false;
    },
    error: (error: any) => {
      console.log(error)
      
      this.loading=false;
    },
})



}


}
