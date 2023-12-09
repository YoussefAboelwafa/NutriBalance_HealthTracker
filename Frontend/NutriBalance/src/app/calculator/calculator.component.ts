import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-calculator',
  templateUrl: './calculator.component.html',
  styleUrls: ['./calculator.component.css'],
})
export class CalculatorComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}

  userGDA: any = {
    weight: null,
    height: null,
    age: null,
    gender: 'male',
    activity: 'low',
  };
  calculateGDA() {
    console.log(this.userGDA);
  }
}
