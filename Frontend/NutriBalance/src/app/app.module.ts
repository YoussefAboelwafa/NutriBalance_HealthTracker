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
import { FpPopupComponent } from './fp-popup/fp-popup.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ModalPopServiceService } from './_services/modal-pop-service.service';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';

@NgModule({
  declarations: [
    SigninComponent,
    AppComponent,
    HomeComponent,
    SignupComponent,
    CoachSignupComponent,
    RoleTypeComponent,
    FpPopupComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatInputModule,
    MatButtonModule,
    MatFormFieldModule
  ],
  providers: [AuthService,authInterceptorProviders,Shared,ModalPopServiceService,FpPopupComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
