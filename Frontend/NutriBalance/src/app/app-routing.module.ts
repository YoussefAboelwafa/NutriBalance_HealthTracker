import { CalculatorComponent } from './calculator/calculator.component';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './signup/signup.component';
import { SigninComponent } from './signin/signin.component';
import { HomeComponent } from './home/home.component';
import { WaitingCoachesComponent } from './waiting-coaches/waiting-coaches.component';
import { NgModule } from '@angular/core';
import { CoachSignupComponent } from './coach-signup/coach-signup.component';
import { RoleTypeComponent } from './role-type/role-type.component';
import { AdminpageComponent } from './adminpage/adminpage.component';
import { CoachPageComponent } from './coach-page/coach-page.component';
import { CreateplanComponent } from './createplan/createplan.component';
import { ViewSubscriptionsComponent } from './view-subscriptions/view-subscriptions.component';
import { CoachProfileComponent } from './coach-profile/coach-profile.component';
import { UserpageComponent } from './userpage/userpage.component';

import { SubscribeComponent } from './subscribe/subscribe.component';

import { CoachPlansComponent } from './coach-plans/coach-plans.component';
import { VerifiedComponent } from './verified/verified.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { FoodCalorie } from './Objects/FoodCalorie';
import { FoodCaloriesComponent } from './food-calories/food-calories.component';
import { ProgressComponent } from './progress/progress.component';
import { UserPlanComponent } from './user-plan/user-plan.component';
import { UserChatComponent } from './user-chat/user-chat.component';
import { UserReportComponent } from './user-report/user-report.component';
import { CoachChatComponent } from './coach-chat/coach-chat.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  },

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
    path: 'coach-signup',
    component: CoachSignupComponent,
  },
  {
    path: 'role-type',
    component: RoleTypeComponent,
  },
  {
    path: 'adminpage',
    component: AdminpageComponent,
    children: [
      {
        path: 'waiting-coaches',
        component: WaitingCoachesComponent,
      },
    ]
  },
  {
    path: 'coach-page',
    component: CoachPageComponent,
    children: [
      {
        path: 'coachprofile',
        component: CoachProfileComponent,
      },
      {
        path: 'showplans',
        component: CoachPlansComponent,
      }
      ,
      {
        path: 'createplan',
        component: CreateplanComponent,
      },
      {
        path: 'view-subscriptions',
        component: ViewSubscriptionsComponent,
      },
      {
        path:'coachchat',
        component:CoachChatComponent,
      }
    ],
  },
  {
    path:'verify/:token',
    component:VerifiedComponent
  },

  {
    path: 'userpage',
    component: UserpageComponent,
    children: [
      {
        path: 'calculator',
        component: CalculatorComponent,
      },
      {

        path: 'subscribe',
        component: SubscribeComponent,
      },
      {
        path: 'userprofile',
        component: UserProfileComponent,
      },
      {

      path: 'Progress',
      component: ProgressComponent,
    },
      {
        path: 'foodcalories',
        component: FoodCaloriesComponent,
      },
      {
        path: 'userplan',
        component: UserPlanComponent,
      },
      {
        path: 'userchat',
        component: UserChatComponent,
      },
      {
        path: 'userreport',
        component: UserReportComponent,
      },

    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
