import { Routes } from '@angular/router';
import { SignupComponent } from './signup/signup.component';
import { SigninComponent } from './signin/signin.component';
import { HomeComponent } from './home/home.component';
import { RoleTypeComponent } from './role-type/role-type.component';
import { CoachSignupComponent } from './coach-signup/coach-signup.component';

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
    path: 'role-type',
    component: RoleTypeComponent,
  },
  {
  path: 'coach-signup',
  component: CoachSignupComponent,
},
];
