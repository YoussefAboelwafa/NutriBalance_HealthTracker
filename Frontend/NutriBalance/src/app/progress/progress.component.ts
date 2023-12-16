import { Component, OnInit } from '@angular/core';
import {Chart} from 'chart.js/auto';
import { TokenStorageService } from '../_services/token-storage.service';
import { Weight } from '../Objects/Weight';
import { User } from '../Objects/User';
import { UserService } from '../_services/user.service';
import { formatDate } from '@angular/common';
@Component({
  selector: 'app-progress',
  templateUrl: './progress.component.html',
  styleUrls: ['./progress.component.css']
})
export class ProgressComponent implements OnInit {
  CurrentUser! :User;
  Weights!: Weight[];
  dateArray!:any[];
  WeightsArray!:any[];
  formattedDates!:any[];



constructor(private token:TokenStorageService, private userservices:UserService){
  this.CurrentUser = this.token.getUser();
 
}

ngOnInit(): void {
  this.userservices.GetWeights(this.CurrentUser['user_id']).subscribe({
    next: (response: any) => {
      this.Weights = response;
      this.dateArray = this.Weights.map((weight: { date: any }) => weight.date);
      this.WeightsArray = this.Weights.map((weight: { weight: any }) => weight.weight);
      
      this.formattedDates = this.dateArray.map((dateString: string) => {
        const inputDate = new Date(dateString);
        return inputDate.toLocaleDateString('en-US', { month: 'long', day: 'numeric' });
      });

      this.createChart()
    },
    error: (error: any) => {
      console.error('Error updating user:', error);
   
    },
  });

}


chartOptions = {
  scales: {
    x: {
      ticks: {
        color: 'black',
        font: {
          weight: 'bold',
          style: 'italic'
        },
        beginAtZero: false,
      }
    },
    y: {
      ticks: {
        font: {
          weight: 'bold'
        },
        color: 'black'
      }
    }
  }
};

chart:any;



private createChart(): void {
  this.chart = new Chart(
    document.getElementById('Progress') as HTMLCanvasElement,
    {
      type: 'line',
      data: {
        labels: this.formattedDates,
        datasets: [
          {
            label: 'Weight (KG)',
            data: this.WeightsArray,
            borderColor: 'rgb(210,125,10)',
            borderWidth: 2 
          }
        ]
      },
      options: {}
});

this.chart.options = this.chartOptions;
this.chart.update();


}





}