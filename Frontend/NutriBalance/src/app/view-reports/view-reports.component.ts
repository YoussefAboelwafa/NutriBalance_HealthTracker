import { Component, OnInit,ChangeDetectorRef } from '@angular/core';
import { AdminService } from '../Service/admin.service';
import { Report } from '../Objects/Report';
import { User } from '../Objects/User';
@Component({
  selector: 'app-view-reports',
  templateUrl: './view-reports.component.html',
  styleUrls: ['./view-reports.component.css']
})
export class ViewReportsComponent implements OnInit {
  reports: Report[]=[]
  loaded!:boolean
  user_spinner:boolean[]=[]
  coach_spinner:boolean[]=[]
  discard_spinner:boolean[]=[]
  constructor(private adminservice:AdminService,private cdr: ChangeDetectorRef) { }



  load_reports(){
    this.adminservice.getReports().subscribe(
      (data) => {
        this.reports = data;
        this.loaded=true
        for(var i=0;i<this.reports.length;i++){
          this.user_spinner[i]=false
          this.coach_spinner[i]=false
          this.discard_spinner[i]=false
        }
      },
      (error) => {
        console.log(error);
        this.loaded=true
        for(var i=0;i<this.reports.length;i++){
          this.user_spinner[i]=false
          this.coach_spinner[i]=false
          this.discard_spinner[i]=false
        }
      }
    );

  }
  ngOnInit(): void {
    this.loaded=false
    this.load_reports()

  }
  delete_user(i:number){
    this.user_spinner[i]=true
    this.adminservice.deleteUser(this.reports[i].user.user_id).subscribe({
      next: (response: any) => {
        this.load_reports()
      },
      error: (error: any) => {
        console.log(error)
        this.load_reports()
      },
    })
   
  }
  delete_coach(i:number){
    this.coach_spinner[i]=true
    this.adminservice.deleteCoach(this.reports[i].coach.coach_id).subscribe({
      next: (response: any) => {
        this.load_reports()
      },
      error: (error: any) => {
        this.load_reports()
      },
    })
    
  }
  discard(i:number){
    this.discard_spinner[i]=true
     this.adminservice.deleteReport(this.reports[i].user.user_id,this.reports[i].coach.coach_id).subscribe({
      next: (response: any) => {
        this.discard_spinner[i]=false
      },
      error: (error: any) => {
        this.discard_spinner[i]=false
      },
    })
    this.reports.splice(i, 1);

  }

}
