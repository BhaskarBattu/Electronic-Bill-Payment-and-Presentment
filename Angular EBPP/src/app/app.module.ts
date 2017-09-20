import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {AngularFireModule} from "angularfire2"

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { MembersComponent } from './members/members.component';
import { AuthGuard} from "./auth.service";
import {routes} from "./app.route"
import {AngularFireAuth} from "angularfire2/auth";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {HomeModule} from './home/home.module';
import {MdDatepickerModule, MdToolbarModule} from '@angular/material';
import { BillsandpaymentmanagementComponent } from './billsandpaymentmanagement/billsandpaymentmanagement.component';
import {ServicesModule} from './services/services.module';

export  const firebaseConfig= {
  apiKey: "AIzaSyBSj1N7PycoJajJv_M1tGyPu0b7-svCLkA",
  authDomain: "fir-first-b86da.firebaseapp.com",
  databaseURL: "https://fir-first-b86da.firebaseio.com",
  projectId: "fir-first-b86da",
  storageBucket: "fir-first-b86da.appspot.com",
  messagingSenderId: "847647218315"
};

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    MembersComponent
  ],
  imports: [
    BrowserModule, MdToolbarModule, MdDatepickerModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpModule,
    HomeModule,
    ServicesModule,
    AngularFireModule.initializeApp(firebaseConfig),
    routes
  ],
  providers: [AuthGuard, AngularFireAuth],
  bootstrap: [AppComponent]
})
export class AppModule { }
