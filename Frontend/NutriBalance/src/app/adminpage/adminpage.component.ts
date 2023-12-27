import { Shared } from './../common/shared';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-adminpage',
  templateUrl: './adminpage.component.html',
  styleUrls: ['./adminpage.component.css'],
})
export class AdminpageComponent {
  constructor(private router: Router, private shared: Shared) {
    this.shared.home = false;
  }

  logout() {
    alert('Logged Out Successfully');
    this.shared.home = false;
    this.router.navigate(['home']);
  }
}
