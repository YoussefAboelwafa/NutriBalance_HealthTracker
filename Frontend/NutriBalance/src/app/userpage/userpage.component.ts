import { Component, OnInit } from '@angular/core';
import { User } from '../Objects/User';
import { TokenStorageService } from '../_services/token-storage.service';
@Component({
  selector: 'app-userpage',
  templateUrl: './userpage.component.html',
  styleUrls: ['./userpage.component.css']
})
export class UserpageComponent implements OnInit {

  constructor(
    private tokenstorage: TokenStorageService,
  ) { }
  user: User = this.tokenstorage.getUser();
  view_subscribe: boolean = true;
  ngOnInit(): void {
 if(this.user.plan!=null){
   this.view_subscribe=false;
  }

}
}
