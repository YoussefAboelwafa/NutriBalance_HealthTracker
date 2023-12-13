import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
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
import { ConfirmationPageComponent } from './confirmation-page/confirmation-page.component';
import { AdminpageComponent } from './adminpage/adminpage.component';
import { CommonModule } from '@angular/common';
import { WaitingCoachesComponent } from './waiting-coaches/waiting-coaches.component';
import { CoachPageComponent } from './coach-page/coach-page.component';
import { CreateplanComponent } from './createplan/createplan.component';
import { ViewSubscriptionsComponent } from './view-subscriptions/view-subscriptions.component';
import { CoachProfileComponent } from './coach-profile/coach-profile.component';
import { UserpageComponent } from './userpage/userpage.component';
import { CalculatorComponent } from './calculator/calculator.component';
import { SubscribeComponent } from './subscribe/subscribe.component';

@NgModule({
  declarations: [
    SigninComponent,
    AppComponent,
    HomeComponent,
    SignupComponent,
    CoachSignupComponent,
    RoleTypeComponent,
    FpPopupComponent,
    ConfirmationPageComponent,
    AdminpageComponent,
    WaitingCoachesComponent,
    CoachPageComponent,
    CreateplanComponent,
    ViewSubscriptionsComponent,
    CoachProfileComponent,
    UserpageComponent,
    CalculatorComponent,
    SubscribeComponent,
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
    MatFormFieldModule,
    CommonModule,
    ReactiveFormsModule,
  ],
  providers: [
    AuthService,
    authInterceptorProviders,
    Shared,
    ModalPopServiceService,
    FpPopupComponent,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
