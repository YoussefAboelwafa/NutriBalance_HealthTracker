import { Component, ElementRef, OnInit, Renderer2 } from '@angular/core';
import { TokenStorageService } from '../_services/token-storage.service';
import { Coach } from '../Objects/Coach';
@Component({
  selector: 'app-coach-page',
  templateUrl: './coach-page.component.html',
  styleUrls: ['./coach-page.component.css']
})
export class CoachPageComponent implements OnInit {

  constructor(private el: ElementRef,private renderer: Renderer2,private tokenstorage: TokenStorageService) { }


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
  


}


export interface RouteInfo {
  path: string;
  title: string;
  icon: string;
  class: string;
}

export const ROUTES: RouteInfo[] = [
  { path: '/coach-page/coachprofile', title: 'Profile', icon: 'fa fa-user-edit icon', class: '' },
];



