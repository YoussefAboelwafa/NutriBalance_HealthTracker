import { validateHorizontalPosition } from '@angular/cdk/overlay';
import { Component, NgModule, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatDialogModule } from '@angular/material/dialog';
import { User } from '../Objects/User';
import { TokenStorageService } from '../_services/token-storage.service';
import { Weight } from '../Objects/Weight';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-calculator',
  templateUrl: './calculator.component.html',
  styleUrls: ['./calculator.component.css'],
})
export class CalculatorComponent implements OnInit {
  constructor(private dialog: MatDialog, private token:TokenStorageService,
    private userservices: UserService) {}

  show_table: boolean = true;
  show_result: boolean = false;
  show_info: boolean = false;
  
  loggeduser!  :User;
  newuserr!  :User;

  bmr = 0;
  tdee = 0;
  bmi = 0;

  protein = 0;
  carb = 0;
  fat = 0;

  pos = '0%';

  ngOnInit(): void {}

  userGDA: any = {
    weight: null,
    height: null,
    age: null,
    gender: 'male',
    activity: 'low',
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
    let pointPosition = (this.bmi / 55) * 100;
    if (pointPosition > 100) pointPosition = 100;
    if (pointPosition < 0) pointPosition = 0;
    this.pos = `${pointPosition}%`;

    // Protein
    this.protein = Math.round(this.userGDA.weight * 0.8 * 100) / 100;

    // Carbohydrates
    this.carb = Math.round(((this.tdee * 0.55) / 4) * 100) / 100;

    // Fat
    this.fat = Math.round(((this.tdee * 0.2) / 9) * 100) / 100;
  }

  saveWeight(){
     this.loggeduser = this.token.getUser();
    const currentDate = new Date(2023, 11, 31);    

    console.log(this.loggeduser);

    this.userservices.AddWeight(currentDate, this.userGDA.weight, this.loggeduser['user_id']).subscribe({
      next: (response: any) => {
        console.log('User updated successfully:', response);
        this.token.saveUser(response);
      },
      error: (error) => {
        console.error('Error updating user:', error);    
      },
    });
  }


  getPointColor() {
    if (this.bmi < 18) {
      return 'lightgreen'; // Color for Underweight
    } else if (this.bmi >= 18 && this.bmi < 25) {
      return 'green'; // Color for Normal weight
    } else if (this.bmi >= 25 && this.bmi < 35) {
      return 'yellow'; // Color for Overweight
    } else {
      return 'red'; // Color for Obesity
    }
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
