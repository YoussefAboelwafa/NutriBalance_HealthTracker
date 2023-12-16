import { Component, OnInit } from '@angular/core';
import { Shared } from '../common/shared';
import { User } from '../Objects/User';
import {
  DomSanitizer,
  SafeResourceUrl,
  SafeUrl,
} from '@angular/platform-browser';
import { UserService } from '../_services/user.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { ModalPopServiceService } from '../_services/modal-pop-service.service';
declare const $: any;
@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})

export class UserProfileComponent implements OnInit {

  user! :User;
  flag: boolean = true;
  emptyFields: string[] = [];
  selectedImage: any;
  hasPhoto = true;
  isEdit = false;
  hasPlan=true;
  originalUser!: User;
  imageUrl: any;
  activeTab: string = 'about';
  defaultImageUrl: string = '../../assets/images/nophoto.png';
  spinner_flag: boolean = false;

  constructor(
    private storage: TokenStorageService,
    private userservices: UserService,
    private sanitizer: DomSanitizer,
    private modalpopup: ModalPopServiceService,
    private shared:Shared
  ) {}

  ngOnInit(): void {
    this.user = this.storage.getUser();
    this.originalUser = { ...this.user };
    console.log(this.user);
    this.emptyFields = [];
    for (const key of Object.keys(this.user)) {
      if (!this.user[key]) {
        if (key == 'image') this.hasPhoto = false;
        if(key=='plan') this.hasPlan=false;
        this.emptyFields.push(key);
      }
    }
    if (this.hasPhoto) {
      this.imageUrl = this.convertToImage(this.user.image);
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
  convertToByteArray(url: any) {}
  handleImageInput(event: any): void {
    const file = event.target.files[0]; // Get the selected file
    if (file) {
      this.selectedImage = file;
      const formData = new FormData();
      formData.append('file', this.selectedImage);
      this.userservices.addImage(this.user.email, formData).subscribe({
        next: (response: any) => {
          this.storage.saveUser(response);
          this.imageUrl = this.convertToImage(response.image);
        },
        error: (error: any) => {
          console.log(error);
        },
      });
    }
  }

  toggleEditMode() {
    this.isEdit = true;
  }

  saveChanges() {
   
    this.spinner_flag = true;
    const hasChanges = !this.areObjectsEqual(this.user, this.originalUser);

    if (hasChanges) {
     
      this.emptyFields = this.getEmptyFields(this.user);
      console.log('saving');
      console.log(this.user);

      this.userservices.updateUser(this.user).subscribe({
        next: (response: any) => {
          console.log('User updated successfully:', response);
          this.storage.saveUser(response);
          this.originalUser = { ...this.user };
          this.spinner_flag = false;

          $('#exampleModalCenter').modal('hide');
          $('#notify').modal('show');
        },
        error: (error) => {
          console.error('Error updating user:', error);
       
        },
      });

      this.isEdit = false;

    } else {
      console.log('No changes detected');
      this.toggleEditMode();
    }
  }
  close() {
    $('#exampleModalCenter').modal('hide');
    $('#notify').modal('hide');
  }

  areObjectsEqual(obj1: any, obj2: any): boolean {

    return JSON.stringify(obj1) === JSON.stringify(obj2);
  }

  getEmptyFields(obj: any): string[] {
    const emptyFields: string[] = [];

    for (const key of Object.keys(obj)) {
      if (!obj[key]) {
        emptyFields.push(key);
      }
    }

    return emptyFields;
  }


  setActiveTab(tab: string) {
    this.activeTab = tab;
  }
}
