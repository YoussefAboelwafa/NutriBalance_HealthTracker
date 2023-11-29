import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterOutlet } from '@angular/router';
import { HomeComponent } from '../home/home.component';
import { SignupComponent } from '../signup/signup.component';
import { HttpClientModule } from '@angular/common/http';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators  } from '@angular/forms';
import { TokenStorageService } from '../_services/token-storage.service';
import { AppConstants } from '../common/app.constants';
import { UserService } from '../_services/user.service';
import { AuthService } from '../_services/auth.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { authInterceptorProviders } from '../_helpers/auth.interceptor';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css'],
})
// export class SigninComponent implements OnInit {
//   loginForm!: FormGroup;

//   constructor(private formBuilder: FormBuilder,private router: Router) { }



//   ngOnInit(): void {
//     this.loginForm = this.formBuilder.group({
//       email: ['', Validators.required],
//       password: ['', Validators.required]
//     });
//   }

//   login(): void {
//     if (this.loginForm.invalid) {
//       return;
//     }

//     console.log("hamo 3ady")
//   }

//   googleLogin(): void {
//     console.log("hamo google")
//   }
//   home() {
//     this.router.navigate(['/home']);
//   }
// }

export class SigninComponent implements OnInit {

  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  currentUser: any;
  googleURL = AppConstants.GOOGLE_AUTH_URL;

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService,private router: Router, private route: ActivatedRoute, private userService: UserService) {}

  ngOnInit(): void {
	const token = this.route.snapshot.queryParamMap.get('token');
	const error = this.route.snapshot.queryParamMap.get('error');
  	if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.currentUser = this.tokenStorage.getUser();
      console.log(this.currentUser)
    }
  	else if(token){
  		this.tokenStorage.saveToken(token);
      console.log(token)
  		this.userService.getCurrentUser().subscribe(
  		      data => {
  		        this.login(data);
  		      },
  		      err => {
  		        this.errorMessage = err.error.message;
  		        this.isLoginFailed = true;
  		      }
  		  );
  	}
  	else if(error){
  		this.errorMessage = error;
	    this.isLoginFailed = true;
  	}
  }

  onSubmit(): void {
    this.authService.login(this.form).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.login(data.user);
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }
    home() {
    this.router.navigate(['/home']);
  }
  login(user:any): void {
	this.tokenStorage.saveUser(user);
	this.isLoginFailed = false;
	this.isLoggedIn = true;
	this.currentUser = this.tokenStorage.getUser();
  console.log(this.currentUser)
  console.log("LOGIN SUCCESS");
    // window.location.reload();
  }

}
