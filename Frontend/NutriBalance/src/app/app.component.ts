import { Component} from '@angular/core';

import { Router} from '@angular/router';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'NutriBalance';

  constructor(private router: Router) {
    // const previousUrl = localStorage.getItem('previousUrl');
    // if (previousUrl) {
    //   this.router.navigateByUrl(previousUrl);
    //   localStorage.removeItem('previousUrl');
    // }
  }
}
