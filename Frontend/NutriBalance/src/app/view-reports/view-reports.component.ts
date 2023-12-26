import { Component, OnInit } from '@angular/core';
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
  constructor(private adminservice:AdminService) { }

  ngOnInit(): void {
     this.adminservice.getReports().subscribe(
      (data) => {
        this.reports = data;
      },
      (error) => {
        console.log(error);
      }
    );
  }
  delete_user(i:number){
    this.adminservice.deleteUser(this.reports[i].user)
  }
  delete_coach(i:number){
    this.adminservice.deleteCoach(this.reports[i].coach)
  }

}
