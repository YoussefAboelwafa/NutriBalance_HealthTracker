import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterOutlet } from '@angular/router';
import { HomeComponent } from '../home/home.component';
import { SignupComponent } from '../signup/signup.component';
import { HttpClientModule, HttpParams } from '@angular/common/http';

import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { TokenStorageService } from '../_services/token-storage.service';
import { AppConstants } from '../common/app.constants';
import { UserService } from '../_services/user.service';
import { AuthService } from '../_services/auth.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { authInterceptorProviders } from '../_helpers/auth.interceptor';
import { RoleTypeComponent } from '../role-type/role-type.component';
import { CoachService } from '../Service/coach.service';
import { Shared } from '../common/shared';
import { ModalPopServiceService } from '../_services/modal-pop-service.service';

import { FpPopupComponent } from '../fp-popup/fp-popup.component';
import { Coach } from '../Objects/Coach';
import { User } from '../Objects/User';
declare const $: any;
@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css'],
})
export class SigninComponent {
  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  currentUser: any;
  googleURL = AppConstants.GOOGLE_AUTH_URL;
  email: any;
  password: any;
  role = this.shared.selectedrole;
  flag_show_login = false;
  flag_btn_login = true;
  isUser = true
  loading=false;


  constructor(private shared: Shared, private userservice: UserService, private coachservice: CoachService, private authService: AuthService, private tokenStorage: TokenStorageService, private router: Router, private route: ActivatedRoute, private pop_service: ModalPopServiceService, private shred: Shared) { }


  ngOnInit(): void {
    const token = this.route.snapshot.queryParamMap.get('token');
    const error = this.route.snapshot.queryParamMap.get('error');
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.currentUser = this.tokenStorage.getUser();
      console.log(this.currentUser);
    } else if (token) {
      this.tokenStorage.saveToken(token);
      console.log(token);
      this.userservice.getCurrentUser().subscribe(
        (data) => {
          this.login(data);
        },
        (err) => {
          this.errorMessage = err.error.message;
          this.isLoginFailed = true;
        }
      );
    } else if (error) {
      this.errorMessage = error;
      this.isLoginFailed = true;
    }
    if (this.shared.selectedrole == 'user') {
      this.isUser = true
    }
    else {
      this.isUser = false
    }
  }

  home() {
    this.router.navigate(['/home']);
  }
  login(user: any): void {
    this.tokenStorage.saveUser(user);
    this.isLoginFailed = false;
    this.isLoggedIn = true;
    this.shared.loggedIn = true;

    this.currentUser = this.tokenStorage.getUser();
    console.log(this.currentUser);
    console.log('LOGIN SUCCESS');
    // window.location.reload();
    this.router.navigate(['home']);
  }


  flag_forget_spinner = false;
  onSubmit(event: any) {
    this.loading=true
    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(this.email)) {
      alert('Invalid email format');
    }

    else if (this.role == 'user') {
      this.userservice.checksignin(this.email, this.password).subscribe(data => {
        console.log(data);
        if (data == null) {
          alert('wrong email or password');
          this.loading=false}
        else {

          let user:User=data;

          this.shared.loggedIn = true;
          this.tokenStorage.saveUser(user);
          this.loading=false
          this.router.navigate(['/userpage']);
        }
      });
    }
    else if (this.role == 'coach') {

      this.coachservice
        .checksignin(this.email, this.password)
        .subscribe((data) => {
          console.log(data);
          if (data == null){ 
            this.loading=false
            alert('wrong email or password');
          }
          else {
            let coach: Coach = data;
            if (coach.isapproved == 0) {
              alert('Sorry, Your account is not approved yet');
            } else {
              this.shared.loggedIn = true;
              this.tokenStorage.saveCoach(coach);
              this.loading=false
              this.router.navigate(['/coach-page']);
            }
          }

        }
        );
    }
    else if (this.role == 'admin') {
      this.loading=false
      this.router.navigate(['/adminpage']);
    }
  }
  forget_pass_first_step(email: any) {
    this.flag_forget_spinner = true
    this.authService.forgetPassword(email, this.role).subscribe(
      (res) => {
        this.flag_forget_spinner = false
        this.email = email;
        $('#forget_pass_send_email').modal('hide');
        $('#verify_email_to_change').modal('show');
      },
      (err) => {
        this.flag_forget_spinner = false
        $('#forget_pass_send_email').modal('hide');
        this.pop_service.pop_up("Email is not registered", "Forget Password");
      }
    );
  }
  forget_pass_second_step(verify_code: any) {
    this.flag_forget_spinner = true
    this.flag_forget_spinner = false
    console.log(verify_code)
    console.log(this.email)
    this.authService.checkOtp(verify_code, this.email).subscribe(
      (res) => {
        this.flag_forget_spinner = false
        $('#verify_email_to_change').modal('hide');
        $('#change_pass').modal('show');
      },
      (err) => {
        this.flag_forget_spinner = false
        $('#verify_email_to_change').modal('hide');
        this.pop_service.pop_up("Code is invalid", "Forget Password");
      }
    );
  }
  forget_pass_final_step(new_pass: any) {

    this.flag_forget_spinner = true
    if (new_pass.length < 8) {
      this.flag_forget_spinner = false
      this.pop_service.pop_up("Password must be at least 8 characters", "Forget Password");
      return;
    }
    
    this.authService.resetPassword(this.email, new_pass, this.role).subscribe(
      (res) => {
        this.flag_forget_spinner = false
        $('#change_pass').modal('hide');
      },
      (err) => {
        this.flag_forget_spinner = false
        this.pop_service.pop_up("Error : Try Again", 'Forget password');
        return;
      }
    );
  }
  close() {
    $('#forget_pass_send_email').modal('hide');
    $('#verify_email_to_change').modal('hide');
    $('#change_pass').modal('hide');
  }
}
