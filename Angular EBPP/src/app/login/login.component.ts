import { Component, OnInit, HostBinding } from '@angular/core';
import { Router } from '@angular/router';
import { moveIn } from '../router.animations';
import {AngularFireAuth, AngularFireAuthModule} from "angularfire2/auth";
import {AngularFireModule} from "angularfire2";
import * as firebase from 'firebase/app';
import {LoginService} from '../services/login.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']/*,
  animations: [moveIn()],
  host: {'[@moveIn]': ''}*/
})
export class LoginComponent implements OnInit {
  getResponseFromDb: any;
  error: any;
  constructor(public af: AngularFireAuth, private loginService: LoginService, private router: Router) {

    this.af.authState.subscribe(auth => {
      if (auth) {

        this.loginService.loginWithOAuth(auth.displayName, auth.email)
          .subscribe((loginResponse) => {
            window.localStorage.setItem('customerMail', auth.email);
            this.getResponseFromDb = loginResponse;
          });
         this.router.navigateByUrl('/home');
      }
    });

  }


  loginGoogle() {
    this.af.auth.signInWithPopup(new firebase.auth.GoogleAuthProvider()
    ).then(
      (success) => {
        window.location.reload();
         this.router.navigate(['/home']);
      }).catch(
      (err) => {
        this.error = err;
      });
  }


  ngOnInit() {
  }

}
