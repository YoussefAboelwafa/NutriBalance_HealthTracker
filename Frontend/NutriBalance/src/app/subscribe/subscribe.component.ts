import { Component, OnInit } from '@angular/core';
import { PlanService } from '../_services/plan.service';
import { Plan } from '../Objects/Plan';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { UserService } from '../_services/user.service';
import { User } from '../Objects/User';
import { TokenStorageService } from '../_services/token-storage.service';
import { ModalPopServiceService } from '../_services/modal-pop-service.service';
declare const $: any;
@Component({
  selector: 'app-subscribe',
  templateUrl: './subscribe.component.html',
  styleUrls: ['./subscribe.component.css'],
})
export class SubscribeComponent implements OnInit {
  constructor(
    private planservice: PlanService,
    private sanitizer: DomSanitizer,
    private userservice: UserService,
    private tokenstorage: TokenStorageService,
    private modalpopup: ModalPopServiceService
  ) {}
  plans: Plan[] = [];
  images: SafeUrl[] = [];
  user: User = this.tokenstorage.getUser();
  spinner_flag: boolean = false;
  i: number = 0;
  loading_spinner: boolean = false;
  ngOnInit(): void {
    this.loading_spinner = true;
    this.planservice.getAllPlans().subscribe((data: any) => {
      this.plans = data;
      for (let i = 0; i < this.plans.length; i++) {
        this.images[i] = this.convertToImage(this.plans[i].coach?.image);
      }
      console.log(this.plans);
      this.loading_spinner = false;
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
  updateindex(i: number) {
    this.i = i;
  }
  subscribe() {
    this.spinner_flag = true;
    console.log(this.user);
    this.userservice
      .subscribe(this.plans[this.i].planName, this.user.user_id)
      .subscribe({
        next: (data) => {
          console.log(data);
          let newuser: User = data;
          this.tokenstorage.saveUser(data);
          this.spinner_flag = false;
          $('#exampleModalCenter').modal('hide');
          $('#notify').modal('show');
        },
        error: (err) => {
          console.log(err);
          this.modalpopup.pop_up('something went wrong', 'error');
        },
      });
  }
  close() {
    $('#exampleModalCenter').modal('hide');
    $('#notify').modal('hide');
    window.location.reload();
  }
}
