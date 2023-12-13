import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Coach } from '../Objects/Coach';
import { CoachService } from '../Service/coach.service';
import { SigninComponent } from '../signin/signin.component';
import { HomeComponent } from '../home/home.component';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-coach-signup',
  templateUrl: './coach-signup.component.html',
  styleUrls: ['./coach-signup.component.css']
})
export class CoachSignupComponent {

  label: String = 'Upload CV';

  constructor(private router: Router, private coachservice: CoachService) { }

  coach = new Coach();
  confirmedPassword: any;
  selectedFile !: File;
  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    this.label = event.target.files[0]?.name || 'upload cv';
  }
  signin() {
    this.router.navigate(['/signin']);
  }
  signup() {
    this.coach.isapproved = 0;

    if (this.coach.password != this.confirmedPassword) {
      alert('Passwords do not match');
    } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(this.coach.email)) {
      alert('Invalid email format');
    } else if (!/^[+]?[\d]+$/.test(this.coach.contact_number)) {
      alert('Invalid contact number');
    } else if (!/^[a-zA-Z0-9_]+$/.test(this.coach.username)) {
      alert('Invalid username format (only letters, numbers, and _)');
    }

    const formData = new FormData();
    formData.append('file', this.selectedFile, this.selectedFile.name);
    formData.append('coach', JSON.stringify(this.coach));

    this.coachservice.saveCoach(formData).subscribe
      (data => {
        alert("You have successfully sign up, please verify your mail!");
      },
        error => {
          alert('Username or email already exists');
        }
      );
  }
}
