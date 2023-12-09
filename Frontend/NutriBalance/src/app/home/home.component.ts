import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { UserService } from '../_services/user.service';
import {
  HttpClient,
  HttpClientModule,
  HttpHandler,
} from '@angular/common/http';
import { TokenStorageService } from '../_services/token-storage.service';
import { authInterceptorProviders } from '../_helpers/auth.interceptor';
import { Shared } from '../common/shared';

@Component({
  selector: 'app-home',

  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  curuser: any;
  constructor(
    private router: Router,
    private userService: UserService,
    private tokenService: TokenStorageService,
    private shared: Shared
  ) {
    if (this.shared.loggedIn == true) {
      setTimeout(function () {
        alert('successfully logged in');
      }, 1000);
    }
  }
  content!: string;
  ngOnInit(): void {
    this.userService.getPublicContent().subscribe(
      (data) => {
        this.content = data;
      },
      (err) => {
        this.content = JSON.parse(err.error).message;
      }
    );
  }

  signin() {
    this.shared.signin_flag = true;
    this.router.navigate(['/role-type']);
  }
  signup() {
    this.shared.signin_flag = false;
    this.router.navigate(['/role-type']);
  }

  onMenuClick(option: string) {
    console.log(option);
    if (option == 'Sign In') {
      this.shared.signin_flag = true;
      this.router.navigate(['/role-type']);
    } else if (option == 'SignUp') {
      this.shared.signin_flag = false;
      this.router.navigate(['/role-type']);
    }
  }
}
