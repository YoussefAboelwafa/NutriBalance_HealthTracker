import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { UserService } from '../_services/user.service';
import { HttpClient, HttpClientModule, HttpHandler } from '@angular/common/http';
import { TokenStorageService } from '../_services/token-storage.service';
import { authInterceptorProviders } from '../_helpers/auth.interceptor';

@Component({
  selector: 'app-home',

  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  curuser:any
  constructor(private router: Router,private userService: UserService, private tokenService:TokenStorageService) { }
  content!: string;
  ngOnInit(): void {
    this.userService.getPublicContent().subscribe(
      data => {
        this.content = data;
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );
  }

  signin(){
    this.router.navigate(['/signin']);
  }
  signup(){
    this.router.navigate(['/signup']);
  }
}
