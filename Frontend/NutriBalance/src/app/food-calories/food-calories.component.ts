import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatRipple } from '@angular/material/core';
import { FoodCalorie } from '../Objects/FoodCalorie';
import { UserService } from '../_services/user.service';
@Component({
  selector: 'app-food-calories',
  templateUrl: './food-calories.component.html',
  styleUrls: ['./food-calories.component.css']
})
export class FoodCaloriesComponent implements OnInit {
 
  displayedColumns: string[] = ['food_name', 'calorie'];
  dataSource!: MatTableDataSource<FoodCalorie>;
  @ViewChild('paginator')
  paginator!: MatPaginator;

  @ViewChild(MatRipple)
  filterValue = 'filter';
  filter="filter";
  foodcalorie: FoodCalorie[] = [];
  constructor(private userservice: UserService) { }

  ngOnInit(): void {

    this.userservice.getFoodCalorie().subscribe(
      (data) => {
        this.foodcalorie=data
        this.dataSource = new MatTableDataSource(this.foodcalorie);
        this.dataSource.paginator = this.paginator;
      },
      (error) => {
        console.log(error);
      }
    );



    
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  applyFilter() {



    if(this.filterValue==""){
      this.filter="filter"
    }
    else{
      this.filter="";
    }
    
    this.dataSource.filter = this.filterValue.trim().toLowerCase();
  }

  onClearFilter() {
    this.filterValue = '';
    this.applyFilter();
  }
  

}
