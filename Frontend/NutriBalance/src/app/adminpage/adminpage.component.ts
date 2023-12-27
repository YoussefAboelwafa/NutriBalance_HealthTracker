import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-adminpage',
  templateUrl: './adminpage.component.html',
  styleUrls: ['./adminpage.component.css']
})
export class AdminpageComponent implements OnInit{

  constructor(private router:Router,private storage:TokenStorageService){}

  admin:any
  ngOnInit(): void {
    this.admin=this.storage.getUser()
    if (this.admin==null) {
      this.router.navigate(['home'])
    }
  }
  logout(){
    this.storage.signOut()
    this.router.navigate(['home'])
  }



}
