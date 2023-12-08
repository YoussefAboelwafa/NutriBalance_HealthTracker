import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './signup/signup.component';
import { SigninComponent } from './signin/signin.component';
import { HomeComponent } from './home/home.component';
import{WaitingCoachesComponent} from './waiting-coaches/waiting-coaches.component';
import { NgModule } from '@angular/core';
import {CoachSignupComponent} from "./coach-signup/coach-signup.component";
import {RoleTypeComponent} from "./role-type/role-type.component";
import { AdminpageComponent } from './adminpage/adminpage.component';
import { CoachPageComponent } from './coach-page/coach-page.component';
export const routes: Routes = [
  {
    path: 'signup',
    component: SignupComponent,
  },
  {
    path: 'signin',
    component: SigninComponent,
  },
  {
    path: 'home',
    component: HomeComponent,
  },
  {
    path: 'waiting-coaches',
    component: WaitingCoachesComponent,
  },
  {
    path: 'coach-signup',
    component: CoachSignupComponent,
  },
  {
    path: 'role-type',
    component: RoleTypeComponent,
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  },
  {
    path: 'adminpage',
    component: AdminpageComponent,
  },
  {
    path: 'coach-page',
    component: CoachPageComponent,
  },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
