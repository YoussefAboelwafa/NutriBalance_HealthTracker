import { Component, OnInit } from '@angular/core';
import { ChatService } from '../_services/chat.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { Coach } from '../Objects/Coach';
import { DomSanitizer } from '@angular/platform-browser';
import { SafeUrl } from '@angular/platform-browser';
import { User } from '../Objects/User';
import { CoachService } from '../Service/coach.service';
@Component({
  selector: 'app-coach-chat',
  templateUrl: './coach-chat.component.html',
  styleUrls: ['./coach-chat.component.css'],
})
export class CoachChatComponent implements OnInit {
  constructor(
    public chat: ChatService,
    private tokenStorageService: TokenStorageService,
    private sanitizer: DomSanitizer,
    private coachService: CoachService
  ) {}
  coach: Coach = this.tokenStorageService.getCoach();
  chats: chat[] = [];
  subscribed_users: User[] = [];
  filteredUsers: User[] = [];
  users_images: any[] = [];
  currUserImage: any;
  coachimage: any;
  user!: User;
  msg: any;
  last_index: number = 0;
  sent: boolean = true;
  unseen: number[] = [];
  temporary: Map<number, number> = new Map();
  filterValue = '';
  filter = 'Search';
  loading: boolean = true;
  ngOnInit(): void {
    this.coachService
      .get_subscriped_users(this.coach.coach_id)
      .subscribe((data) => {
        this.subscribed_users = data;
        this.filteredUsers = data;
        for (let i = 0; i < this.subscribed_users.length; i++) {
          this.chat
            .get_unseen_chats(
              this.subscribed_users[i].user_id,
              this.coach.coach_id
            )
            .subscribe((data) => {
              this.temporary.set(this.subscribed_users[i].user_id, data);
            });
        }
        console.log(this.temporary);
        for (let i = 0; i < this.filteredUsers.length; i++) {
          this.users_images.push(
            this.convertToImage(this.subscribed_users[i].image)
          );
        }

        this.coachimage = this.convertToImage(this.coach.image);
        this.loading = false;
      });
  }
  convertToImage(string: any) {
    const binaryString = atob(string);
    const binaryData = new Uint8Array(binaryString.length);
    for (let i = 0; i < binaryString.length; i++) {
      binaryData[i] = binaryString.charCodeAt(i);
    }
    const blob = new Blob([binaryData], { type: 'application/image' });
    const blobUrl = URL.createObjectURL(blob);
    return this.sanitizer.bypassSecurityTrustUrl(blobUrl) as SafeUrl;
  }
  choosechat(index: number, user_id: number) {
    const myButton = document.getElementById(index.toString());
    const oldButton = document.getElementById(this.last_index.toString());
    if (myButton) {
      oldButton?.classList.remove('active');
      myButton.addEventListener('click', function () {
        myButton.classList.add('active');
      });
      this.last_index = index;
      this.temporary.set(user_id, 0);
      this.chat.set_seen(user_id, this.coach.coach_id).subscribe((data) => {});
    }

    this.chat.get_user_chat(user_id).subscribe((data) => {
      this.chats = data;
      console.log(this.chats);
      this.user = this.subscribed_users[index];
      this.currUserImage = this.users_images[index];
    });
  }
  sendmessage() {
    this.sent = false;
    let ct = new chat();
    ct.message = this.msg;
    ct.sent_by = 'coach';
    this.chats.push(ct);
    let temp = this.msg;
    this.msg = '';
    this.chat
      .saveChatByCoach(this.user.user_id, this.coach.coach_id, temp)
      .subscribe((data) => {
        console.log(data);
        this.sent = true;
      });
  }
  applyFilter() {
    if (this.filterValue == '') {
      this.filter = 'filter';
    } else {
      this.filter = '';
    }
    this.filteredUsers = this.subscribed_users.filter((user) =>
      user.username.toLowerCase().includes(this.filterValue.toLowerCase())
    );
    this.users_images = this.filteredUsers.map((user) =>
      this.convertToImage(user.image)
    );
  }
}
class chat {
  message: any;
  sent_by: any;
}
