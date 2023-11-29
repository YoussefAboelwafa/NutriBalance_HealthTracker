import { Component, NgModule } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { Sign } from 'crypto';
import { SigninComponent } from './signin/signin.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, SigninComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'NutriBalance';

  constructor(private router: Router) {
    this.router.navigate(['/adminpage']);
  }
}
