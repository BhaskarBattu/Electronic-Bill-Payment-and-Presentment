import {NgModule} from '@angular/core';
import {HomeComponent} from './home.component';
import {MdDatepickerModule, MdNativeDateModule, MdToolbarModule} from '@angular/material';
import {CommonModule} from '@angular/common';
import {BrowserModule} from '@angular/platform-browser';
import {CustomerModule} from '../customer/customer.module';
import {BillsmanagementModule} from '../billsmanagement/billsmanagement.module';
import {BillsandpaymentmanagementComponent} from '../billsandpaymentmanagement/billsandpaymentmanagement.component';
import {BillsandpaymentmanagementModule} from '../billsandpaymentmanagement/billsandpaymentmanagement.module';

@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    MdToolbarModule,
    MdDatepickerModule,
    MdNativeDateModule,
    CommonModule,
    BrowserModule,
    CustomerModule,
    BillsmanagementModule,
    BillsandpaymentmanagementModule
  ],
  providers: []
})
export class HomeModule { }
