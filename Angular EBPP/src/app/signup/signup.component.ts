import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { moveIn, fallIn } from '../router.animations';
import {AngularFireAuth, AngularFireAuthModule} from "angularfire2/auth";
import {AngularFireModule} from "angularfire2";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']/*,
  animations: [moveIn(), fallIn()],
  host: {'[@moveIn]': ''}*/
})
export class SignupComponent implements OnInit {

  state: string = '';
  error: any;
  email: string='';
  password: string='';
  constructor(public af: AngularFireAuth, private router: Router) {

  }

  onSubmit(formData) {
    if(formData.valid) {

      console.log(formData.value);
      this.af.auth.createUserWithEmailAndPassword( formData.value.email, formData.value.password)
        .then(
        (success) => {
          this.router.navigate(['/members']);
        }).catch(
        (err) => {
          this.error = err;
        });
    }
  }

  ngOnInit() {
  }

}
