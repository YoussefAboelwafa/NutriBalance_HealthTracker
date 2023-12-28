import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../Objects/User';
import { TokenStorageService } from '../_services/token-storage.service';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { UserService } from '../_services/user.service';
import { ChatService } from '../_services/chat.service';
declare const $: any;
@Component({
  selector: 'app-user-plan',
  templateUrl: './user-plan.component.html',
  styleUrls: ['./user-plan.component.css'],
})
export class UserPlanComponent implements OnInit {
  constructor(
    private router: Router,
    private tokenStorageService: TokenStorageService,
    private sanitizer: DomSanitizer,
    private userservice: UserService,
    private chatservice: ChatService
  ) {}
  user = new User();
  coachimage!: SafeUrl;
  spinner_flag: boolean = false;
  isnull: boolean = false;
  ngOnInit(): void {
    this.user = this.tokenStorageService.getUser();
    console.log(this.user);
    if (this.user.coach == null) {
      this.isnull = true;
    } else {
      this.coachimage = this.convertToImage(this.user.coach?.image);
    }
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
  close() {
    $('#exampleModalCenter').modal('hide');
    $('#notify').modal('hide');
    window.location.reload();
  }
  Delete() {
    this.spinner_flag = true;
    this.userservice.deletesubscription(this.user.user_id).subscribe({
      next: (data) => {
        this.chatservice
          .deletechatByUser(this.user.user_id)
          .subscribe((res) => {
            this.spinner_flag = false;
            console.log(data);
            let newuser: User = data;
            this.tokenStorageService.saveUser(newuser);
            $('#exampleModalCenter').modal('hide');
            $('#notify').modal('show');
          });
      },
      error: (err) => {
        console.log(err);
        this.spinner_flag = false;
      },
    });
  }
}
