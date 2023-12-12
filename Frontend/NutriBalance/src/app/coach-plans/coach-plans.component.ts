import { Component, OnInit } from '@angular/core';
import { Plan } from '../Objects/Plan';
import { TokenStorageService } from '../_services/token-storage.service';
import { CoachService } from '../Service/coach.service';
import { Coach } from '../Objects/Coach';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { EditDialogComponent } from '../edit-dialog/edit-dialog.component';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-coach-plans',
  templateUrl: './coach-plans.component.html',
  styleUrls: ['./coach-plans.component.css']
})
export class CoachPlansComponent implements OnInit {


  plans: Plan[] = []
  coach!: Coach
  constructor(private storage: TokenStorageService, private service: CoachService, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.coach = this.storage.getCoach()
    this.service.getCoachPlans(this.coach.coach_id).subscribe(data => {
      this.plans = data
    })
  }
  delete_plan(plan: Plan) {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '300px',
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result == "confirm") {
        this.service.deletePlan(plan.planName).subscribe(data => {
          if (data =="Plan deleted")
           window.location.reload()
           else 
           console.log(result)
        })
      }
    });
  }
  openEditDialog(plan: Plan) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.data = plan
    dialogConfig.width = '50%';
    dialogConfig.height = '50%';
    const dialogRef = this.dialog.open(EditDialogComponent, dialogConfig);
    dialogRef.afterClosed().subscribe((data: any) => {


      if (data) {
        this.openConfirmDialog(data)
      }
    });
  }
  openConfirmDialog(data: any): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '300px',
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result == "confirm") {
        this.service.updatePlan(data).subscribe(data => {
          window.location.reload()
        })
      }
    });
  }



}
