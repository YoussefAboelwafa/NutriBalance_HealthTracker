import { Component, NgModule } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { Sign } from 'crypto';
import { SigninComponent } from './signin/signin.component';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, SigninComponent, CommonModule, HttpClientModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'NutriBalance';

  constructor(private router: Router) {
    this.router.navigate(['/home']);
  }
}
