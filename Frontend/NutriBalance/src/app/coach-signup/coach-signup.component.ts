import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-coach-signup',
  standalone: true,
  imports: [CommonModule,RouterOutlet,FormsModule],
  templateUrl: './coach-signup.component.html',
  styleUrl: './coach-signup.component.css'
})
export class CoachSignupComponent {


}
