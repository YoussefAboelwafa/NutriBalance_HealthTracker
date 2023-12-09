import {Component, NgModule} from '@angular/core';
import {NavigationEnd, Router, RouterOutlet} from '@angular/router';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'NutriBalance';

  constructor(private router: Router) {
    //make the default screen is the home component and if the window reloded go to it
    // this.router.events.subscribe((event) => {
    //   if (event instanceof NavigationEnd && event.url === '/') {
    //     // Reloaded the window, navigate to the home component
    //     this.router.navigate(['/']);
    //   }
    // });

    // this.router.navigate(['coach-page']);

  }
}
