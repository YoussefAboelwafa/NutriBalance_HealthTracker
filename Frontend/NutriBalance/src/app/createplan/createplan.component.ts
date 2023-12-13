import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Plan } from '../Objects/Plan';
import { Coach } from '../Objects/Coach';
import { PlanService } from '../_services/plan.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { Router } from '@angular/router';
import { ModalPopServiceService } from '../_services/modal-pop-service.service';
declare const $: any;
@Component({
  selector: 'app-createplan',
  templateUrl: './createplan.component.html',
  styleUrls: ['./createplan.component.css'],
})
export class CreateplanComponent implements OnInit {
  form: FormGroup;
  plan = new Plan();
  spinner_flag: boolean = false;
  //validators for form
  constructor(
    private fb: FormBuilder,
    private planService: PlanService,
    private tokenStorageService: TokenStorageService,
    private router: Router,
    private modalpopup: ModalPopServiceService
  ) {
    this.form = this.fb.group({
      planName: ['', Validators.required],
      goal: ['', Validators.required],
      planDescription: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.plan.goal = 'cut';
  }
  onSubmit(): void {
    this.spinner_flag = true;
    let coach: Coach = this.tokenStorageService.getCoach();
    this.plan.coach = coach;
    this.planService.savePlan(this.plan).subscribe((data) => {
      if (data != null) {
        this.spinner_flag = false;
        $('#exampleModalCenter').modal('hide');
        $('#notify').modal('show');
      } else {
        this.spinner_flag = false;
        $('#exampleModalCenter').modal('hide');
        $('#error').modal('show');
        this.plan.planName = '';
        this.plan.description = '';
      }
    });
  }
  close() {
    $('#exampleModalCenter').modal('hide');
    $('#notify').modal('hide');
    //todo: navigate to view-plans page
    this.router.navigate(['/coach-plans']);
  }
  closeError() {
    $('#exampleModalCenter').modal('hide');
    $('#notify').modal('hide');
    $('#error').modal('hide');
  }
}
