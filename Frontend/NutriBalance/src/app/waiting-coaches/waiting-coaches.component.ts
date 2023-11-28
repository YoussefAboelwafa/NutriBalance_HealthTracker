import { Component, OnInit } from '@angular/core';
import { CommonModule, NgIf } from '@angular/common';
import { CoachService } from '../Service/coach.service';
import { Coach } from '../Objects/Coach';
import { HttpClient, HttpHandler, HttpHeaders } from '@angular/common/http';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
@Component({
  selector: 'app-waiting-coaches',
  standalone: true,
  providers: [CoachService,HttpClient],
  imports: [CommonModule],
  templateUrl: './waiting-coaches.component.html',
  styleUrl: './waiting-coaches.component.css'
})
export class WaitingCoachesComponent implements OnInit{
  coaches: Coach[] = [];
  selectedFile!: File ;
  cvBlobUrl?: SafeResourceUrl;
  flag:boolean=true;
  constructor(private coachservice:CoachService,private sanitizer: DomSanitizer) { }
  ngOnInit(): void {
    this.coachservice.getwaitingcoaches().subscribe(
      (data) => {
        this.coaches = data;
        console.log(this.coaches);
      },
      (error) => {
        console.log(error);
      }
    );
  }
  view_cv(i:number){
     var cv = this.coaches[i]?.cv;
    if (cv) {
      const uint8Array = new Uint8Array(atob(cv).split('').map(char => char.charCodeAt(0)));

      const blob = new Blob([uint8Array], { type: 'application/pdf' });

      // Create a Blob URL for the Blob
      const blobUrl = URL.createObjectURL(blob);
      this.cvBlobUrl = this.sanitizer.bypassSecurityTrustResourceUrl(blobUrl);
    }
  }
  approve(i:number){
    this.coachservice.approveCoach(this.coaches[i].coach_id).subscribe(
      (data) => {
        console.log(data);
      this.coaches.splice(i,1);
      },
      (error) => {
        console.log(error);
      }
    );
  }
  decline(i:number){
    console.log('d5l')
    this.coachservice.deleteCoach(this.coaches[i].coach_id).subscribe(
      (data) => {
        console.log(data);
      this.coaches.splice(i,1);
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
