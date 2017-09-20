import { Component, OnInit } from '@angular/core';
import {BillsdetailsService} from '../services/billsdetails.service';
import {Router} from '@angular/router';
import {BillsList} from '../model/billsList';
import {DatePipe} from '@angular/common';
import {NgForm} from '@angular/forms';
import {CustomerslistService} from '../services/customerslist.service';
import {CustomersList} from '../model/CustomersList';

@Component({
  selector: 'app-billsmanagement',
  templateUrl: './billsmanagement.component.html',
  styleUrls: ['./billsmanagement.component.css']
})
export class BillsmanagementComponent implements OnInit {
  jsoobn: any;
  searchBar: any;
  billsResponse: any;
  isUploadedBills: boolean;
  startDate: any;
  sDate: any;
  eDate: any;
  endDate: any;
  retriveBillsList: BillsList[];
  searchBillsList: BillsList[];
  date; isCustomerBills: boolean;
  onrefreshChange: boolean;
  orderElement = null;
  customerBillsList: CustomersList [];


  constructor(private billsList: BillsdetailsService, private customerInfo: CustomerslistService,
              private router: Router) { }

  uploadDatasource(event) {
    const fileList: File = event.target.files[0];
    const reader: FileReader = new FileReader();

    reader.onload = (e) => {
      const csv: string = reader.result;
      this.jsoobn = this.csvJSON(csv);
      this.billsList.uploadBillsDetails(this.jsoobn)
        .subscribe((response) => {
          if (response.json()) {
            alert('Bills added successfully');
           // this.isUploadedBills = true;
          } else {
            alert('Bills are not added beacause some customers are not found ');
            this.router.navigate(['/home/customermanagement']);
          }
          this.billsResponse  = response; }
      );
    };
    reader.readAsText(fileList);
  }

  public csvJSON(csv) {
    const lines = csv.split('\n');
    const result = [];
    const headers = lines[0].split(',');

    for (let i = 1; i < lines.length; i++) {

      const obj = {};
      const currentline = lines[i].split(',');

      for (let j = 0; j < headers.length; j++) {
        obj[headers[j]] = currentline[j];
      }
      result.push(obj);
    }
    const rr = result.pop();
    return JSON.stringify(result);
  }
  ngOnInit() {
    this.onrefreshChange = true;
    this.isCustomerBills = false;
    this.billsList.getBills()
      .subscribe((response) => { this.retriveBillsList = response.json()});
    this.date = new Date();
  }


  searchOnBillsEmailOrName(term: string): void {
    this.startDate = null;
    this.endDate = null;
    this.isCustomerBills = false;
    if (term) {
      this.billsList.searchBills(term)
        .subscribe(data => {
          console.log(data);
          this.searchBillsList = data.json().slice(0, 4); }
        );
      this.isUploadedBills = true;
      this.onrefreshChange = false;
    } else {
      this.searchBillsList = [];
      this.isUploadedBills = false;
      this.onrefreshChange = true;
    }
  }



onSubmit(datePickerValue: NgForm) {
      this.searchBar = null;
  this.isCustomerBills = false;
    const datePipe = new DatePipe('en-US');
    if (datePickerValue.value.startDate.getTime() > datePickerValue.value.endDate.getTime()) {
      this.onrefreshChange = false;

      alert('End date is lower than Starting date');
    }else {
  this.sDate = datePipe.transform(datePickerValue.value.startDate, 'y-M-d');
  this.eDate = datePipe.transform(datePickerValue.value.endDate, 'y-M-d');
  this.billsList.searchBillsBasedonDate( this.sDate, this.eDate)
    .subscribe(data => {
      console.log(data.json().length);
     if (data.json().length !== 0) {
       this.isUploadedBills = true;
       this.onrefreshChange = false;
     }else {
       this.isUploadedBills = false;
       this.onrefreshChange = true;
       alert('No Results');
     }
      this.searchBillsList = data.json().slice(0, 4); }
    );
  }
}
  setSortingElement(element){
    this.orderElement = element;
  }
  billsListToView() {
    this.searchBar = null;
    this.startDate = null;
    this.endDate = null;
    this.onrefreshChange = true;
    this.isUploadedBills = false;
    this.isCustomerBills = false;

    this.billsList.getBills()
      .subscribe((response) => { this.retriveBillsList = response.json()});
  }

  clickBillCustomer(email: any) {
    this.startDate = null;
    this.endDate = null;
    this.searchBar = null;
    this.onrefreshChange = false;
    this.isCustomerBills = true;
    this.isUploadedBills = false;

    this.customerInfo.getCustomerBills(email)
      .subscribe((response) => {
      console.log(response);
       this.customerBillsList = response.json();
      });
  }
}
