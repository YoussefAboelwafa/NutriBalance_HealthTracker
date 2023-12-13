import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../_services/auth.service';
@Component({
  selector: 'app-verified',
  templateUrl: './verified.component.html',
  styleUrls: ['./verified.component.css']
})
export class VerifiedComponent implements OnInit{
  constructor(private router: Router,private route :ActivatedRoute,private authservice:AuthService) {
  }
  isVerified=false;
  go() {
    this.isVerified=true;
    // this.router.navigate(['']);
  }
  token: any;
  ngOnInit() {
    this.isVerified=false;
    this.token = this.route.snapshot.paramMap.get('token')!;
    this.authservice.verify(this.token).subscribe(
      data => {
        // this.go()
        this.isVerified=true;
      },
      err => {
        alert("The verification code is invalid")
        // this.router.navigate(['']);
      }
    );
    

  }
}
