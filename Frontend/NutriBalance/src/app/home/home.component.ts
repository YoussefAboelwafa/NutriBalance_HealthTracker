import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { UserService } from '../_services/user.service';
import { HttpClient, HttpClientModule, HttpHandler } from '@angular/common/http';
import { TokenStorageService } from '../_services/token-storage.service';
import { authInterceptorProviders } from '../_helpers/auth.interceptor';
import { Shared } from '../common/shared';
import {
  DomSanitizer,
  SafeResourceUrl,
  SafeUrl,
} from '@angular/platform-browser';

@Component({
  selector: 'app-home',

  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  curuser: any
  role: string = ''
  isLogin = false;
  hasPhoto = false
  imageUrl: any
  defaultImageUrl: string = '../../assets/images/nophoto.png';

  constructor(private router: Router, private userService: UserService, private tokenService: TokenStorageService, private shared: Shared, private sanitizer: DomSanitizer,) {
    // if (this.shared.loggedIn == true) {
    //   setTimeout(function () {
    //     alert("successfully logged in");
    //   }, 1000);
    // }
  }
  content!: string;
  ngOnInit(): void {
    this.curuser = this.tokenService.getUser();
    console.log(this.curuser)
    if (this.curuser) {
      this.isLogin = true
      this.role = "user"
    }
    else {
      this.curuser = this.tokenService.getCoach();
      if (this.curuser) {
        this.isLogin = true
        this.role = "coach"
      }
    }
    if (this.curuser) {
      if (this.curuser.image) {
        this.hasPhoto = true
        this.imageUrl = this.convertToImage(this.curuser.image)
      }
    }

  }

  signin() {
    this.shared.signin_flag = true;
    this.router.navigate(['/role-type']);
  }
  signup() {
    this.shared.signin_flag = false;
    this.router.navigate(['/role-type']);
  }

  onMenuClick(option: string) {
    console.log(option);
    if (option == "Sign In") {
      this.shared.signin_flag = true;
      this.router.navigate(['/role-type']);
    }
    else if (option == "SignUp") {
      this.shared.signin_flag = false;
      this.router.navigate(['/role-type']);

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

  isUserSidebarOpen = false;
  toggleUserSidebar() {
    this.isUserSidebarOpen = !this.isUserSidebarOpen;
  }
  closeUserSidebar() {
    this.isUserSidebarOpen = false;
  }
  logout(): void {
    this.tokenService.signOut()
    this.shared.loggedIn=false;
    window.location.reload();
  }
  goToAdminPage() {
    this.router.navigateByUrl('/adminpage');
  }
  goToCoachPage() {
    this.router.navigateByUrl('/coach-page');
  }
  goToUserPage() {
    this.router.navigateByUrl('/userpage');
  }

}
