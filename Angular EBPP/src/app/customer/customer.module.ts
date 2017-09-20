import {NgModule} from '@angular/core';
import {MdToolbarModule} from '@angular/material';
import {CommonModule} from '@angular/common';
import {BrowserModule} from '@angular/platform-browser';
import {CustomerComponent} from '../customer/customer.component';
import {Ng2OrderModule} from 'ng2-order-pipe';
import {FormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    CustomerComponent
  ],
  imports: [
    MdToolbarModule,
    CommonModule,
    BrowserModule,
    Ng2OrderModule,
    FormsModule,
  ],
  providers: []
})
export class CustomerModule { }
