import { Component, OnInit } from '@angular/core';
import { ChatService } from '../_services/chat.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { User } from '../Objects/User';
import { DomSanitizer } from '@angular/platform-browser';
import { SafeUrl } from '@angular/platform-browser';
@Component({
  selector: 'app-user-chat',
  templateUrl: './user-chat.component.html',
  styleUrls: ['./user-chat.component.css'],
})
export class UserChatComponent implements OnInit {
  constructor(
    private chatservice: ChatService,
    private tokenstorageservice: TokenStorageService,
    private sanitizer: DomSanitizer
  ) {}
  user: User = this.tokenstorageservice.getUser();
  chats: chat[] = [];
  coachimage: any;
  userimage: any;
  msg: any;
  spinner_flag: boolean = false;
  ngOnInit(): void {
    this.chatservice.get_user_chat(this.user.user_id).subscribe((data) => {
      this.chats = data;
      console.log(this.chats);
    });
    this.coachimage = this.convertToImage(this.user.coach?.image);
    this.userimage = this.convertToImage(this.user.image);
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
  sendmessage() {
    this.spinner_flag = true;
    this.chatservice
      .saveChat(this.user.user_id, this.user.coach?.coach_id, this.msg)
      .subscribe((data) => {
        console.log(data);
        let ct = new chat();
        ct.message = this.msg;
        ct.sent_by = 'user';
        ct.seen = 0;
        this.chats.push(ct);
        this.msg = '';
        this.spinner_flag = false;
      });
  }
}
class chat {
  message: any;
  sent_by: any;
  seen: any;
}
