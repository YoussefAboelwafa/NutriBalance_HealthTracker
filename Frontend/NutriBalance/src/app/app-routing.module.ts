import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './signup/signup.component';
import { SigninComponent } from './signin/signin.component';
import { HomeComponent } from './home/home.component';
import{WaitingCoachesComponent} from './waiting-coaches/waiting-coaches.component';
import { CoachSignupComponent } from './coach-signup/coach-signup.component';
import { NgModule } from '@angular/core';
import { AdminpageComponent } from './adminpage/adminpage.component';


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
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  {
    path: 'waiting-coaches',
    component: WaitingCoachesComponent,
  },
  {
    path: 'coach-signup',
    component: CoachSignupComponent,
  },
  {
    path: 'adminpage',
    component: AdminpageComponent,
  },
  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
