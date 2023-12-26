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
  styleUrls: ['./coach-chat.component.css']
})
export class CoachChatComponent implements OnInit {

  constructor(
    public chat: ChatService,
    private tokenStorageService: TokenStorageService,
    private sanitizer: DomSanitizer,
    private coachService:CoachService
  ) {}
coach:Coach=this.tokenStorageService.getCoach();
  chats: chat[] = [];
  subscribed_users:User[]=[];
  users_images: any[] = [];
  currUserImage: any;
  coachimage: any;
  user!:User;
  msg: any;
  last_index:number=0;
  ngOnInit(): void {
    this.coachService.get_subscriped_users(this.coach.coach_id).subscribe((data)=>{
      this.subscribed_users=data;
      console.log(this.subscribed_users);
          for (let i = 0; i < this.subscribed_users.length; i++) {
      this.users_images.push(this.convertToImage(this.subscribed_users[i].image));
    }
    this.coachimage = this.convertToImage(this.coach.image);
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
  choosechat(index:number){
    const myButton = document.getElementById(index.toString());
    const oldButton = document.getElementById(this.last_index.toString());
    if (myButton) {
     oldButton?.classList.remove('active');
      myButton.addEventListener('click', function() {
        myButton.classList.add('active');
      });
      this.last_index=index;
      
    }

    this.chat.get_user_chat(this.subscribed_users[index].user_id).subscribe((data) => {
      this.chats = data;
      console.log(this.chats);
      this.user=this.subscribed_users[index];
      this.currUserImage = this.users_images[index];
    });
  }
  sendmessage(){
    this.chat.saveChatByCoach(this.user.user_id,this.coach.coach_id,this.msg).subscribe((data)=>{
      console.log(data);
      let ct=new chat();
      ct.message=this.msg;
      ct.sent_by='coach';
      this.chats.push(ct);
      this.msg='';
    });

  }

}
class chat {
  message: any;
  sent_by: any;
}
