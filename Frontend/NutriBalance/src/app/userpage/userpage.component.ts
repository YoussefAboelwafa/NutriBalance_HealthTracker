import { Component, OnInit } from '@angular/core';
import { User } from '../Objects/User';
import { TokenStorageService } from '../_services/token-storage.service';
import { Router } from '@angular/router';
import { UserService } from '../_services/user.service';
@Component({
  selector: 'app-userpage',
  templateUrl: './userpage.component.html',
  styleUrls: ['./userpage.component.css']
})
export class UserpageComponent implements OnInit {
  hasNewNotifications = true;

  constructor(
    private tokenstorage: TokenStorageService,
    private router: Router,
    private service: UserService
  ) { }

  notifications: { id: number, message: string, date: Date, type: number, route: string }[] = [
  ];

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
  user: User = this.tokenstorage.getUser();
  view_subscribe: boolean = true;
  ngOnInit(): void {
    if (this.user.plan != null) {
      this.view_subscribe = false;
    }
    this.service.getNotification(this.user.user_id).subscribe(
      {
        next: data => {
          console.log(data)
          data.forEach((element:any) => {
            if(element.type==0){
              this.notifications.push({id:element.notificationId,message:element.message,date:element.date,type:element.type,route:"/"})
            }
            else{
              this.notifications.push({id:element.notificationId,message:element.message,date:element.date,type:element.type,route:"/"})
            }
          });
        },
        error: error => {
          console.error('There was an error!', error);
        }
      }
    )


  }
  logout() {
    this.tokenstorage.signOut();
    this.router.navigateByUrl('/'); // Navigate to the home page
  }
}
