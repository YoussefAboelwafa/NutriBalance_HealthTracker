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
 buttonEnabled:boolean[]=[]
 loaded!:boolean
  constructor(private userservice: UserService,private coachservice:CoachService, private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    this.user = this.tokenStorage.getUser();
    this.loaded=false
    this.coachservice.getCoaches().subscribe(
      (data) => {
        this.coaches = data;
        console.log(this.coaches);
        this.loaded=true
      },
      (error) => {
        console.log(error);
        this.loaded=true
      }
    );
    this.loading=false;
    for(var i=0;i<this.reports.length;i++){
      this.buttonEnabled[i]=false
    }
  }
  isdisabled(i:number){
    return   this.reports[i]==undefined ||  /^\s*$/.test(this.reports[i]) || !this.buttonEnabled[i]
  }
report(i:number){

  this.loading=true;
  this.userservice.addReport(this.user.user_id,this.coaches[i].coach_id,this.reports[i]).subscribe({
    next: (response: any) => {
      this.loading=false;
      for(var j=0;j<this.reports.length;j++){
        this.buttonEnabled[j]=false
      }
    },
    error: (error: any) => {
      console.log(error)
      this.loading=false;
      for(var j=0;j<this.reports.length;j++){
        this.buttonEnabled[j]=false
      }
    },
  })
 
 }
 writing(i:number){
    this.buttonEnabled[i]=true
   for( var j=0;j<this.reports.length;j++){
    if(j==i) {
      continue
    }
    this.buttonEnabled[j]=false
   }
 }

}
