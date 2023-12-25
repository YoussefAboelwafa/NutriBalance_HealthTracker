import { Component, ElementRef, OnInit, Renderer2 } from '@angular/core';
import { TokenStorageService } from '../_services/token-storage.service';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
@Component({
  selector: 'app-coach-page',
  templateUrl: './coach-page.component.html',
  styleUrls: ['./coach-page.component.css']
})
export class CoachPageComponent implements OnInit {


  hasNewNotifications = true;
  constructor(private router:Router,private el: ElementRef,private renderer: Renderer2,private tokenstorage: TokenStorageService) {  
  }
  notifications: { message: string; route: string }[] = [
    { message: 'New message 1', route: '/component1'},
    { message: 'New message 2', route: '/component2'},
  ];

  clearNotification(index: number): void {
    this.notifications.splice(index, 1);
  }
  onMenuOpened() {
    this.hasNewNotifications = false;
  }

  public menuItems!: any[];
  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
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



