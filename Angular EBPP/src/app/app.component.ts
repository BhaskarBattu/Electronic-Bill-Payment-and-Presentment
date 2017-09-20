import { Component } from '@angular/core';
import {AngularFireAuth} from 'angularfire2/auth';
import {Router} from '@angular/router';
import {LogoutService} from './services/logout.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  isloginer: boolean;
  name: any;
  state: string = '';
  url: any;
  phonenumber: any;
  isUserLoggedIn: boolean;
  logoutresponse: any;
  constructor(public af: AngularFireAuth, private logoutService: LogoutService , private router: Router) {

    this.af.authState.subscribe(auth => {
      if (auth) {
        if (auth.email === 'battubhaskar957@gmail.com') {
          this.isloginer = true;
        }
        this.isUserLoggedIn = true;
        this.name = auth.displayName;
        this.url = auth.photoURL;
        this.phonenumber = auth.phoneNumber;
      }
    });

  }
  logout() {

    this.logoutService.logout()
      .subscribe((response) => {
          if (response) {
            this.isUserLoggedIn = false;
            this.router.navigate(['/login']);
            this.af.auth.signOut();
            window.localStorage.removeItem('customerMail');
          }
         this.logoutresponse = response;
        }
      );
  }
}
