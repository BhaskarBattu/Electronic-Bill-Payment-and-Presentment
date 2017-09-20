import {NgModule} from '@angular/core';
import {MdToolbarModule} from '@angular/material';
import {CommonModule} from '@angular/common';
import {BrowserModule} from '@angular/platform-browser';
import {BillsandpaymentmanagementComponent} from './billsandpaymentmanagement.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    BillsandpaymentmanagementComponent
  ],
  imports: [
    MdToolbarModule,
    CommonModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: []
})
export class BillsandpaymentmanagementModule { }
