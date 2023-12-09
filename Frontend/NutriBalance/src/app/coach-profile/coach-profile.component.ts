import { Component, OnInit } from '@angular/core';
import { Coach } from '../Objects/Coach';
import { DomSanitizer, SafeResourceUrl, SafeUrl } from '@angular/platform-browser';
import { CoachService } from '../Service/coach.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { ConnectionPositionPair } from '@angular/cdk/overlay';
import { UrlSerializer } from '@angular/router';

@Component({
  selector: 'app-coach-profile',
  templateUrl: './coach-profile.component.html',
  styleUrls: ['./coach-profile.component.css']
})
export class CoachProfileComponent implements OnInit {

  coach!: Coach
  cvBlobUrl?: SafeResourceUrl;
  flag: boolean = true;
  emptyFields: string[] = [];
  selectedImage: any
  hasPhoto = true
  isEdit = false
  originalCoach!: Coach
  imageUrl: any
  activeTab: string = 'about';
  defaultImageUrl: string = '../../assets/images/nophoto.png';

  constructor(private storage: TokenStorageService, private coachservice: CoachService, private sanitizer: DomSanitizer) { }


  ngOnInit(): void {
    this.coach = this.storage.getCoach()
    this.originalCoach = { ...this.coach }
    console.log(this.coach)
    this.emptyFields = [];
    for (const key of Object.keys(this.coach)) {
      if (!this.coach[key]) {
        if (key == 'image') this.hasPhoto = false
        this.emptyFields.push(key);
      }
    }
    if (this.hasPhoto) {
      this.imageUrl = this.convertToImage(this.coach.image);
    }
    var cv = this.coach.cv
    if (cv) {
      const uint8Array = new Uint8Array(atob(cv).split('').map(char => char.charCodeAt(0)));
      const blob = new Blob([uint8Array], { type: 'application/pdf' });
      const blobUrl = URL.createObjectURL(blob);
      this.cvBlobUrl = this.sanitizer.bypassSecurityTrustResourceUrl(blobUrl);
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
  convertToByteArray(url: any) {

  }
  handleImageInput(event: any): void {
    const file = event.target.files[0]; // Get the selected file
    if (file) {
      this.selectedImage = file;
      const formData = new FormData();
      formData.append('file', this.selectedImage);
      this.coachservice.addImage(this.coach.email, formData).subscribe({
        next: (response) => {
          this.storage.saveCoach(response)
          this.imageUrl = this.convertToImage(this.selectedImage);
        },
        error: (error) => {
          console.log(error);
        },
      });
    }
  }

  toggleEditMode() {
    // this.originalCoach = { ...this.coach }; // Store a deep copy of the coach when entering edit mode
    this.isEdit = !this.isEdit;
  }

  saveChanges() {
    // Check for changes
    const hasChanges = !this.areObjectsEqual(this.coach, this.originalCoach);

    if (hasChanges) {
      // Handle empty fields if needed
      this.emptyFields = this.getEmptyFields(this.coach);
      console.log("saving")
      console.log(this.coach)

      this.coachservice.updateCoach(this.coach).subscribe({
        next: (response) => {
          console.log('Coach updated successfully:', response);
          this.storage.saveCoach(response)
          this.originalCoach = { ...this.coach }
          window.location.reload()
        },
        error: (error) => {
          console.error('Error updating coach:', error);
          // window.location.reload()
        },
      });
      // } else {
      // alert('Empty fields:'+ emptyFields);
      this.toggleEditMode()
      // Handle empty fields as needed (e.g., display a message to the user)
      // }
    }
    else {
      console.log('No changes detected');
      this.toggleEditMode();
    }
  }

  areObjectsEqual(obj1: any, obj2: any): boolean {
    // Implement deep object comparison logic as needed
    // Example: Compare specific fields for equality
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
  handlePdfUpload($event: Event) {
    const file = ($event.target as HTMLInputElement).files![0];
    if (file) {
      const formData = new FormData();
      formData.append('file', file, file.name);
      this.coachservice.addCv(this.coach.email, formData).subscribe({
        next: (response) => {
          this.storage.saveCoach(response)
          const uint8Array = new Uint8Array(atob(response.cv).split('').map(char => char.charCodeAt(0)));
          const blob = new Blob([uint8Array], { type: 'application/pdf' });
          const blobUrl = URL.createObjectURL(blob);
          this.cvBlobUrl = this.sanitizer.bypassSecurityTrustResourceUrl(blobUrl);
          window.location.reload()
        },
        error: (error) => {
          console.log(error);
          window.location.reload()
        },
      });
    }
  }


  setActiveTab(tab: string) {
    this.activeTab = tab;
  }
}