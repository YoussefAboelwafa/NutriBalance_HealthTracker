import { Component, OnInit } from '@angular/core';
import {Chart} from 'chart.js/auto';
import { TokenStorageService } from '../_services/token-storage.service';
import { Weight } from '../Objects/Weight';
import { User } from '../Objects/User';
@Component({
  selector: 'app-progress',
  templateUrl: './progress.component.html',
  styleUrls: ['./progress.component.css']
})
export class ProgressComponent implements OnInit {
  CurrentUser! :User;
  Weights: Weight[] | undefined
  
  data = [
    { year: 2010, count: 10 },
    { year: 2011, count: 20 },
    { year: 2012, count: 15 },
    { year: 2013, count: 25 },
    { year: 2014, count: 22 },
    { year: 2015, count: 30 },
    { year: 2016, count: 28 },
  ];


constructor(private token:TokenStorageService){

  this.CurrentUser = this.token.getUser();
  console.log(this.CurrentUser);
 
  setTimeout(() => {
    this.createChart();
  });
}

ngOnInit(): void {

  }
  

private createChart(): void {
    new Chart(
      document.getElementById('Progress') as HTMLCanvasElement,
      {
        type: 'line',
        data: {
          labels: this.data.map(row => row.year),
          datasets: [
            {
              label: 'Weight',
              data: this.data.map(row => row.count)
            }
          ]
        }
        
      }
    );
  }



}
