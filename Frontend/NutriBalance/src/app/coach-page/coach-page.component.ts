import { Component, ElementRef, OnInit, Renderer2 } from '@angular/core';
import { TokenStorageService } from '../_services/token-storage.service';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
import { CoachService } from '../Service/coach.service';
import { Coach } from '../Objects/Coach';
@Component({
  selector: 'app-coach-page',
  templateUrl: './coach-page.component.html',
  styleUrls: ['./coach-page.component.css']
})
export class CoachPageComponent implements OnInit {


  hasNewNotifications = true;
  constructor(private router:Router,private el: ElementRef,private renderer: Renderer2,private tokenstorage: TokenStorageService,private service: CoachService) {  
  }
  notifications: {id:number, message:string,date:Date,type:number,route:string }[] = [
  ];
  coach!:Coach
  clearNotification(index: number): void {
    this.service.deleteNotification(this.notifications[index].id).subscribe(
      {
        next: data => {
          console.log(data)
          this.notifications.splice(index, 1);
        },
        error: error => {
          console.error('There was an error!', error);
        }
      }
    )
  }
  onMenuOpened() {
    this.hasNewNotifications = false;
  }
  ngOnInit() {
    this.coach=this.tokenstorage.getCoach()  
    this.service.getNotification(this.coach.coach_id).subscribe(
      {
        next: data => {
          console.log(data)
          data.forEach((element:any) => {
            //if type 0 route to subscriptions and if type 1 route to messages
            if(element.type==2 || element.type){
              this.notifications.push({id:element.notificationId,message:element.message,date:element.date,type:element.type,route:"/coach-page/view-subscriptions"})
            }
            else{
              this.notifications.push({id:element.notificationId,message:element.message,date:element.date,type:element.type,route:"/messages"})
            }
          });
        },
        error: error => {
          console.error('There was an error!', error);
        }
      }
    )
  }
  addBodyModificationClass() {
    this.renderer.addClass(this.el.nativeElement.ownerDocument.body, 'body-modification');
  }
  removeBodyModificationClass() {
    this.renderer.removeClass(this.el.nativeElement.ownerDocument.body, 'body-modification');
  }
  onAddButtonClick() {
    this.addBodyModificationClass();
  }
  onRemoveButtonClick() {
    this.removeBodyModificationClass();
  }
  logout() {
    this.tokenstorage.signOut();
    this.router.navigateByUrl('/'); // Navigate to the home page
  }
  


}


export interface RouteInfo {
  path: string;
  title: string;
  icon: string;
  class: string;
}

export const ROUTES: RouteInfo[] = [

  { path: 'coachprofile', title: 'Profile', icon: 'fa fa-user-edit icon', class: '' },
  { path: 'createplan', title: 'Create Plan', icon: 'fa fa-plus-circle icon', class: '' },
  { path: 'view-subscriptions', title: 'View Subscriptions', icon: 'fa fa-eye icon', class: ''}
];



