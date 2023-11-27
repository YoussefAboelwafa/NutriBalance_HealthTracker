import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterOutlet } from '@angular/router';
import { HomeComponent } from '../home/home.component';
import { SigninComponent } from '../signin/signin.component';
import { FormsModule } from '@angular/forms';
import { User } from '../Objects/User';
import { UserService } from '../Services/user.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    SigninComponent,
    HomeComponent,
    FormsModule,
  ],
  providers: [UserService, HttpClient],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css',
})
export class SignupComponent {
  constructor(private router: Router, private userservice: UserService) {}

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
    } else if (!/^[a-zA-Z0-9_]+$/.test(this.user.username)) {
      alert('Invalid username format (only letters, numbers, and _)');
    } else {
      this.userservice.saveUser(this.user).subscribe((data) => {
        if (data == null) {
          alert('Email or Username already exists');
        }
      });
      this.router.navigate(['/home']);
    }
  }
}
