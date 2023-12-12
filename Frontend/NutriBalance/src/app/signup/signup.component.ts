import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterOutlet } from '@angular/router';
import { HomeComponent } from '../home/home.component';
import { SigninComponent } from '../signin/signin.component';
import { FormsModule } from '@angular/forms';
import { User } from '../Objects/User';
import { UserService } from '../_services/user.service';
import { HttpClient } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  constructor(private router: Router, private userservice: UserService,private dialog:MatDialog) {}

  user = new User();
  confirmedPassword: any;

  signin() {
    this.router.navigate(['/home']);
  }

  submit() {
    if (this.user.password != this.confirmedPassword) {
      alert('Passwords do not match');
    } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(this.user.email)) {
      alert('Invalid email format');
    } else if (!/^[+]?[\d]+$/.test(this.user.contact_number)) {
      alert('Invalid contact number');
    } 
    else {
      this.userservice.saveUser(this.user).subscribe((data) => {
        if (data == null) {
          alert('Email or Username already exists');
        }
        else{
          alert("You have successfully sign up, please verify your mail!");
        }
        this.router.navigate(['/home']);
      });
    }
  }
}
