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

  coach!: Coach

  plans: Plan[] =[]

  constructor(private storage: TokenStorageService, private service: CoachService, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.coach = this.storage.getCoach()
    if (this.coach) {
      this.service.getCoachPlans(this.coach.coach_id).subscribe(data => {
        this.plans = data
      })
    }
  }
  delete_plan(plan: Plan) {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '300px',
      data: {
        title: "Confirm Delete",
        body: "Are you sure you want to delete this plan?"
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result == "confirm") {
        // let data="Plan deleted"
        this.service.deletePlan(plan.planName).subscribe(data => {
          if (data == "Plan deleted") {
            //remove the plan from plans array
            this.plans = this.plans.filter(p => p.planName != plan.planName)
            window.location.reload()
          }
          else {
            console.log(result)
          }
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
        this.openConfirmDialog("Are you sure you want to update this information?")
      }
    });
  }
  openConfirmDialog(data: any): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '300px',
      data: {
        title: "Confirm Update",
        body: data
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result == "confirm") {
        this.service.updatePlan(data).subscribe(data => {
          let index = this.plans.findIndex(x => x.planName === data.planName);
          this.plans[index] = data
          window.location.reload()
        })
      }
    });
  }



}
