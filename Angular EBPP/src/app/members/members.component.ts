import { Component, OnInit } from '@angular/core';
// import { AngularFire, AuthProviders, AuthMethods } from 'angularfire2';
import { AngularFireAuthModule } from 'angularfire2/auth';
import { AngularFireAuth } from 'angularfire2/auth';
import { Router } from '@angular/router';
import { moveIn, fallIn, moveInLeft } from '../router.animations';


@Component({
  selector: 'app-members',
  templateUrl: './members.component.html',
  styleUrls: ['./members.component.css'],
  animations: [moveIn(), fallIn(), moveInLeft()],
  host: {'[@moveIn]': ''}
})
export class MembersComponent implements OnInit {
  name: any;
  state: string = '';
  url: any;
  phonenumber: any;
  constructor(public af: AngularFireAuth,private router: Router) {

    this.af.authState.subscribe(auth => {
      if(auth) {
        this.name = auth.displayName;
          this.url=auth.photoURL;
          this.phonenumber =auth.phoneNumber;
          console.log(auth.uid);
          console.log(auth.providerData);
      }
    });

  }

  logout(){
/*    this.af.authState
      // You should now be able to navigate:
      .subscribe(() => this.router.navigate(['/login']));
    // The composed observable completes, so there's no need to unsubscribe.*/
    this.router.navigate(['/login']);
    this.af.auth.signOut();
  }

  ngOnInit() {
  }
}
