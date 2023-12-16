import { Component, OnInit } from '@angular/core';
import { CoachService } from '../Service/coach.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { Coach } from '../Objects/Coach';
import { User } from '../Objects/User';
@Component({
  selector: 'app-view-subscriptions',
  templateUrl: './view-subscriptions.component.html',
  styleUrls: ['./view-subscriptions.component.css'],
})
export class ViewSubscriptionsComponent implements OnInit {
  comment_update: boolean[] = [];
  hide_edit_btn: boolean[] = [];
  comment_text: string[] = [];
  spinner_flag: boolean = false;
  constructor(
    private coachservice: CoachService,
    private tokenstorageservice: TokenStorageService
  ) {}
  coach: Coach = this.tokenstorageservice.getCoach();
  subscribed_users: User[] = [];
  ngOnInit(): void {
    this.coachservice
      .get_subscriped_users(this.coach.coach_id)
      .subscribe((data: any) => {
        this.subscribed_users = data;
        console.log(this.subscribed_users);
        for (let i = 0; i < this.subscribed_users.length; i++) {
          if (this.subscribed_users[i].comment == null) {
            this.comment_text[i] = 'No Comment';
          } else {
            this.comment_text[i] = this.subscribed_users[i].comment;
          }
        }
      });
    for (let i = 0; i < this.subscribed_users.length; i++) {
      this.comment_update[i] = false;
      this.hide_edit_btn[i] = false;
    }
  }
  update_flag(i: number) {
    this.comment_update[i] = true;
    this.hide_edit_btn[i] = true;
  }

  update_comment(i: number) {
    this.spinner_flag = true;
    this.coachservice
      .update_comment(this.comment_text[i], this.subscribed_users[i].user_id)
      .subscribe((data: any) => {
        console.log(data);
        this.comment_update[i] = false;
        this.hide_edit_btn[i] = false;
        this.subscribed_users[i].comment = this.comment_text[i];
        this.spinner_flag = false;
      });
  }
}
