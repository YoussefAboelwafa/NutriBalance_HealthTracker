import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterOutlet } from '@angular/router';
import { HomeComponent } from '../home/home.component';
import { SignupComponent } from '../signup/signup.component';
import { HttpClientModule } from '@angular/common/http';
import {OAuthModule} from 'angular-oauth2-oidc'
import { GoogleApiService } from '../services/google-api.service';

@Component({
  selector: 'app-signin',
  standalone: true,
  imports: [
    CommonModule,
    HttpClientModule,
    OAuthModule
  ],
  templateUrl: './signin.component.html',
  styleUrl: './signin.component.css',
})
export class SigninComponent {
  constructor(private router: Router,private readonly google:GoogleApiService) {}

  home() {
    this.router.navigate(['/home']);
  }
}
