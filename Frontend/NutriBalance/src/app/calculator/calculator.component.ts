import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-calculator',
  templateUrl: './calculator.component.html',
  styleUrls: ['./calculator.component.css']
})
export class CalculatorComponent implements OnInit {

  constructor() { }

  userGDA:any = {
    weigth: 0,
    height: 0,
    age: 0,
    gender:'',
    activity:'',
  }

  ngOnInit(): void {
  }

}
