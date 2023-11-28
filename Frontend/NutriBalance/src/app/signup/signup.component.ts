import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterOutlet } from '@angular/router';
import { HomeComponent } from '../home/home.component';
import { SigninComponent } from '../signin/signin.component';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [CommonModule, RouterOutlet, SigninComponent, HomeComponent],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css',
})
export class SignupComponent {
  constructor(private router: Router) {}

  signin() {
    this.router.navigate(['/signin']);
  }
}
