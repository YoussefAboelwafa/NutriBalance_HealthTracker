import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';


import { authInterceptorProviders } from './_helpers/auth.interceptor';
import { SigninComponent } from './signin/signin.component';
import { HomeComponent } from './home/home.component';
import { SignupComponent } from './signup/signup.component';
import { AuthService } from './_services/auth.service';
import { AppComponent } from './app.component';
import { CoachSignupComponent } from './coach-signup/coach-signup.component';
import { RoleTypeComponent } from './role-type/role-type.component';
import { Shared } from './common/shared';


@NgModule({
  declarations: [
    SigninComponent,
    AppComponent,
    HomeComponent,
    SignupComponent,
    CoachSignupComponent,
    RoleTypeComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [AuthService,authInterceptorProviders,Shared],
  bootstrap: [AppComponent]
})
export class AppModule { }
