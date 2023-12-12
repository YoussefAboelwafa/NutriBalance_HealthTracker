import { validateHorizontalPosition } from '@angular/cdk/overlay';
import { Component, NgModule, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatDialogModule } from '@angular/material/dialog';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-calculator',
  templateUrl: './calculator.component.html',
  styleUrls: ['./calculator.component.css'],
})
export class CalculatorComponent implements OnInit {
  constructor(private dialog: MatDialog, private modalService: NgbModal) {}

  show_table: boolean = false;
  show_result: boolean = true;
  show_info: boolean = false;

  bmr = 0;
  tdee = 0;
  bmi = 0;

  ngOnInit(): void {}

  userGDA: any = {
    weight: 75,
    height: 170,
    age: 21,
    gender: 'male',
    activity: 'medium',
  };
  calculateGDA() {
    this.show_table = false;
    this.show_result = true;
    this.show_info = false;

    // BMR
    if (this.userGDA.gender == 'male') {
      this.bmr = Math.round(
        10 * this.userGDA.weight +
          6.25 * this.userGDA.height -
          5 * this.userGDA.age +
          5
      );
    } else {
      this.bmr = Math.round(
        10 * this.userGDA.weight +
          6.25 * this.userGDA.height -
          5 * this.userGDA.age -
          161
      );
    }

    // TDEE
    if (this.userGDA.activity == 'low') {
      this.tdee = Math.round(this.bmr * 1.2);
    } else if (this.userGDA.activity == 'medium') {
      this.tdee = Math.round(this.bmr * 1.375);
    } else if (this.userGDA.activity == 'high') {
      this.tdee = Math.round(this.bmr * 1.55);
    } else if (this.userGDA.activity == 'extreme') {
      this.tdee = Math.round(this.bmr * 1.725);
    }

    // BMI
    this.bmi =
      Math.round(
        (this.userGDA.weight / (this.userGDA.height / 100) ** 2) * 100
      ) / 100;
  }
  close_result() {
    this.show_table = true;
    this.show_result = false;
    this.show_info = false;
  }
  open_info() {
    this.show_table = false;
    this.show_result = false;
    this.show_info = true;
  }
  close_info() {
    this.show_table = false;
    this.show_result = true;
    this.show_info = false;
  }
}
