import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../_services/token-storage.service';
import { Coach } from '../Objects/Coach';
@Component({
  selector: 'app-coach-page',
  templateUrl: './coach-page.component.html',
  styleUrls: ['./coach-page.component.css']
})
export class CoachPageComponent implements OnInit {

  constructor(private tokenstorage:TokenStorageService) { }

  ngOnInit(): void {
    //just for testing
    let coach:Coach = this.tokenstorage.getCoach();
    console.log(coach);
  }

}
