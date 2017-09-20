import {NgModule} from '@angular/core';
import {MdNativeDateModule, MdToolbarModule} from '@angular/material';
import {CommonModule} from '@angular/common';
import {BrowserModule} from '@angular/platform-browser';
import {BillsmanagementComponent} from './billsmanagement.component';
import {MdDatepickerModule} from '@angular/material';
import {FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {Ng2OrderModule} from 'ng2-order-pipe';

@NgModule({
  declarations: [
    BillsmanagementComponent
  ],
  imports: [
    MdToolbarModule,
    CommonModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    MdNativeDateModule,
    MdDatepickerModule,
    Ng2OrderModule
  ],
  providers: []
})
export class BillsmanagementModule { }
