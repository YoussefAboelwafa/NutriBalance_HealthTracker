import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Plan } from '../Objects/Plan';
import { Coach } from '../Objects/Coach';
import { PlanService } from '../_services/plan.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-createplan',
  templateUrl: './createplan.component.html',
  styleUrls: ['./createplan.component.css']
})
export class CreateplanComponent implements OnInit {

  form: FormGroup;
  plan = new Plan();
  //validators for form
  constructor(private fb: FormBuilder, private planService: PlanService, private tokenStorageService: TokenStorageService, private router: Router) {
    this.form = this.fb.group({
      planName: ['', Validators.required],
      goal: ['', Validators.required],
      planDescription: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.plan.goal = "cut";

  }
  onSubmit(): void {
    let coach: Coach = this.tokenStorageService.getCoach();
    this.plan.coach = coach;
    this.planService.savePlan(this.plan).subscribe(data => {
      if (data != null) {
        alert("Plan created successfully");
        //todo: navigate to view-plans page
        this.router.navigate(['/coach-page']);
      }
      else {
        alert("There is a plan with the same name");
        this.plan.planName = "";
        this.plan.description = "";
      }
    })
  }
}
