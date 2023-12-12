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
  flag_verify=false;
  go() {
    this.flag_verify=true;
    this.router.navigate(['']);
  }
  token: any;
  ngOnInit() {
    this.flag_verify=false;
    this.token = this.route.snapshot.paramMap.get('token')!;
    this.authservice.verify(this.token).subscribe(
      data => {
        this.go()
      },
      err => {
        this.router.navigate(['']);
      }
    );
    

  }
}
