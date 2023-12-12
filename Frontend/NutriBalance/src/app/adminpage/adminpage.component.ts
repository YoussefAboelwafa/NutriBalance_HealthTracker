import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-adminpage',
  templateUrl: './adminpage.component.html',
  styleUrls: ['./adminpage.component.css']
})
export class AdminpageComponent {

  constructor(private router:Router){}

  logout(){
    alert("Logged Out Successfully")
    this.router.navigate(['home'])
  }



}
