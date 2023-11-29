import { Component, Injectable } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterOutlet } from '@angular/router';
import { RoleTypeComponent } from '../role-type/role-type.component';
import { HomeComponent } from '../home/home.component';
import { HttpClient, HttpParams } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { signinService } from '../Services/signin.service';
const baseUrl = 'http://localhost:8080';
@Injectable({
  providedIn: 'root'
})
@Component({
  selector: 'app-signin',
  standalone: true,
  imports: [CommonModule,RouterOutlet,FormsModule,HomeComponent,RoleTypeComponent],
  providers: [HttpClient,signinService],
  templateUrl: './signin.component.html',
  styleUrl: './signin.component.css'
})
export class SigninComponent {

  constructor(private role:RoleTypeComponent,private signinservice:signinService){}
  email: any;
  password : any;
  onSubmit(event: any){
    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(this.email)) {
      alert('Invalid email format');
    }
    const params = new HttpParams()
  .set('email', this.email)
  .set('password',this.password);
    if(this.role.selected_role=='user') {
     this.signinservice.usersignin(params).subscribe(data => {
      if(data==null) alert('wrong email or password');
    });
    }
    else if(this.role.selected_role=='coach'){
      this.signinservice.coachsignin(params).subscribe(data => {
        if(data==null) alert('wrong email or password');
      });
    }
    else if(this.role.selected_role=='admin'){
      this.signinservice.adminsignin(params).subscribe(data => {
        if(data==null) alert('wrong email or password');
      });
    }

  }
}
