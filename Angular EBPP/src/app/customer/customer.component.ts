import { Component, OnInit } from '@angular/core';
import {CustomerslistService} from '../services/customerslist.service';
import {Router} from '@angular/router';
import {CustomersList} from '../model/CustomersList';
import 'rxjs/add/observable/of';
import {Observable} from "rxjs/Observable";

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {
  jsoobn: any;
  custListResponse: any;
  errorMsgInUpLoadcust: any;

  isUploaded: boolean;
  customerslist: CustomersList[];
  searchCustomersList: CustomersList[];
  onrefreshChange: boolean;
  searchBar: any;
  orderElement = null;
  customerBillsList: any;
  isCustomerBills: boolean;
  customerPreviousBills: CustomersList [];
  paginationArrayForCustBill = [];
  paginationArray = [];
  errorMsg: any;
  pagination: any;
  paginationForCustomers: any;
  start: number = 1;
  end: number;
  ispaginationForCustBill: boolean;
  ispaginationForCustList: boolean;

  constructor(private CustList: CustomerslistService, private router: Router) { }

  uploadDatasource(event) {
    const fileList: File = event.target.files[0];
    /*console.log(fileList);
    const reader: FileReader = new FileReader();
    reader.onload = (e) => {
      const csv: string = reader.result;
      this.jsoobn = this.csvJSON(csv);
       console.log(this.jsoobn);
      this.CustList.uploadCustomersList(this.jsoobn)
        .subscribe((response) => {
        console.log(response.json());
        if ( response.json()) {
          this.router.navigate(['/home/customermanagement']);
          alert('Customers list added successfully');
          // this.isUploaded = true;
          this.onrefreshChange = true;
        }
            this.custListResponse  = response}
        );
    };
    reader.readAsText(fileList);*/

    const fileName = fileList.name;


    const payload = {
      fileList,
    }

    const formData: FormData = new FormData();
    formData.append('file', fileList);
    this.CustList.uploadDataSource(formData)
      .subscribe(
        response => {
          if (response.length === 0 ) {
            this.router.navigate(['/home/customermanagement']);
            alert('Customers list added successfully');
          }else {
              alert('Some Fields has wrong credentials');
              for (let i = 0; i < response.length; i++) {
                 this.errorMsgInUpLoadcust = response;
              }
          }

        },
        error => {
          console.log('error', error);
        });
  }

  public csvJSON(csv) {
    const lines = csv.split('\n');
    const result = [];
    const headers = lines[0].split(',');
    if (headers.length === 2) {

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
    } else {
      alert('please enter a valid list');
      this.router.navigate(['/home/customermanagement']);
    }
  }
  ngOnInit() {
    this.onrefreshChange = true;
    this.isCustomerBills = false;
    this.ispaginationForCustBill = true;
    this.ispaginationForCustList = false;

    this.CustList.customersList(0, 3)
      .subscribe((response) => { this.customerslist = response.json(); });
    this.bildPagination();

  }

  bildPagination() {
    this.CustList.getCountofCustomersBillsList()
      .subscribe((response) => {
      this.pagination = response; },
        errormsg => this.errorMsg = errormsg + 'ngoninit',
        () => { this.PaginationArrayCallBackFunction(); }
        );
  }

  PaginationArrayCallBackFunction() {
    this.paginationArrayForCustBill = this.CustList.getPaginationArray(this.pagination);
  }

  loadCustomerBillsList(pageid) {
      this.start = ( pageid - 1) * 3  ;
      this.end =   pageid * 3;
    this.CustList.customersList(this.start, this.end)
      .subscribe((response) => {this.customerslist = response.json(); });
  }





  customersListToView() {
    this.searchBar = null;
    this.isUploaded = true;
    this.ispaginationForCustList = true;

    this.ispaginationForCustBill = false;
    this.onrefreshChange = false;
    this.isCustomerBills = false;



    this.CustList.customersListToView(0, 3)
      .subscribe(data => {
        this.searchCustomersList = data.json(); }
      );
    this.buildPaginationForCustomersList();
  }

  buildPaginationForCustomersList() {
    this.CustList.getCountofCustomersList()
      .subscribe((response) => {
          this.paginationForCustomers = response; },
        errormsg => this.errorMsg = errormsg + 'ngoninit',
        () => { this.PaginationArrayCallBackFunctionForCustList(); }
      );
  }
  PaginationArrayCallBackFunctionForCustList() {
    this.paginationArray = this.CustList.getPaginationArray(this.paginationForCustomers);
  }

  loadCustomersList(pageid) {
    this.start = ( pageid - 1) * 3  ;
    this.end =   pageid * 3;
    this.CustList.customersListToView(this.start, this.end)
      .subscribe((response) => {this.searchCustomersList = response.json(); });
  }


  searchCustomers(term: string): void {
    if (term) {
      this.CustList.searchCustomers(term)
        .subscribe(data => {
          this.searchCustomersList = data.json().slice(0, 4); }
        );
      this.isUploaded = true;
      this.onrefreshChange = false;
      this.isCustomerBills = false;
      this.ispaginationForCustBill = false;
      this.ispaginationForCustList = false;
    } else {
      this.ispaginationForCustList = false;
      this.searchCustomersList = [];
      this.isUploaded = false;
      this.onrefreshChange = true;
      this.ispaginationForCustBill = true;
      this.isCustomerBills = false;
    }

  }
  setSortingElement(element){
    this.orderElement = element;
  }

  getCustomerDetails(customerEmail: any) {
    console.log(customerEmail);
    this.searchBar = null;
    this.onrefreshChange = false;
    this.isCustomerBills = true;
    this.isUploaded = false;

    this.CustList.getCustomerBills(customerEmail)
      .subscribe((response) => {
        this.customerBillsList = response.json();
      });
    this.CustList.customerPreviousBills(customerEmail).
    subscribe((response) => {
      this.customerPreviousBills = response.json();
    });
  }
}
