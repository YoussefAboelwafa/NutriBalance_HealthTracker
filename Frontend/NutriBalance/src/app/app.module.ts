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
import { CoachPlansComponent } from './coach-plans/coach-plans.component';
import { EditDialogComponent } from './edit-dialog/edit-dialog.component';
import { ConfirmDialogComponent } from './confirm-dialog/confirm-dialog.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatSelectModule } from '@angular/material/select';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { VerifiedComponent } from './verified/verified.component';
import { FoodCaloriesComponent } from './food-calories/food-calories.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { ProgressComponent } from './progress/progress.component';
import { UserPlanComponent } from './user-plan/user-plan.component';
import { UserChatComponent } from './user-chat/user-chat.component';
import { ChangePasswordDialogComponent } from './change-password-dialog/change-password-dialog.component';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { CoachChatComponent } from './coach-chat/coach-chat.component';



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
    CoachPlansComponent,
    EditDialogComponent,
    ConfirmDialogComponent,
    UserProfileComponent,
    VerifiedComponent,
    FoodCaloriesComponent,
    ProgressComponent,
    UserPlanComponent,
    UserChatComponent,
    UserPlanComponent,
    ChangePasswordDialogComponent,
    ChangePasswordDialogComponent,
    CoachChatComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MatProgressSpinnerModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatInputModule,
    MatButtonModule,
    MatFormFieldModule,
    CommonModule,
    ReactiveFormsModule,
    MatSidenavModule,
    MatSelectModule,
    MatTableModule,
    MatPaginatorModule,
    MatIconModule,
    MatMenuModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatSelectModule,
    MatTableModule,
    MatPaginatorModule,
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
export class AppModule { }
