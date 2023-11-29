import { Component, NgModule } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { Sign } from 'crypto';
import { SigninComponent } from './signin/signin.component';
import { authInterceptorProviders } from './_helpers/auth.interceptor';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'NutriBalance';

  constructor(private router: Router) {
    // this.router.navigate(['/home']);
  }
}
