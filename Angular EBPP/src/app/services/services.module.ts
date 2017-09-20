import {NgModule} from '@angular/core';
import {MdDatepickerModule, MdToolbarModule} from '@angular/material';
import {CommonModule} from '@angular/common';
import {BrowserModule} from '@angular/platform-browser';
import {LoginService} from './login.service';
import {CustomerslistService} from './customerslist.service';
import {BillsdetailsService} from './billsdetails.service';
import {LogoutService} from './logout.service';

@NgModule({
  declarations: [
  ],
  imports: [
    MdToolbarModule,
    CommonModule,
    BrowserModule,
    MdDatepickerModule
  ],
  providers: [
      LoginService,
    LogoutService,
      CustomerslistService,
      BillsdetailsService
  ]
})
export class ServicesModule { }
