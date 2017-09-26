import { Component, OnInit } from '@angular/core';
import {CustomerslistService} from '../services/customerslist.service';
import {CustomersList} from '../model/CustomersList';
import {Router} from '@angular/router';
import {NgForm} from '@angular/forms';
import {Observable} from 'rxjs/Observable';

@Component({
  selector: 'app-billsandpaymentmanagement',
  templateUrl: './billsandpaymentmanagement.component.html',
  styleUrls: ['./billsandpaymentmanagement.component.css']
})
export class BillsandpaymentmanagementComponent implements OnInit {
  viewSummary: boolean;
  isPay: boolean;
  isPayMail; isPayMonth; isPaybillNo; isPayAmount;
  customerMail: any;
  cardno: any; cvvc: any; expmon: any; expye: any; nameonca: any; paya: any; payb: any; payma: any; paymo: any;
  payamount: any;
  paybillNo: any;
  paymail: any;
  paymonth: any;
  creditOrDebitnumber: any;
  nameOnTheCard: any;
  expMonth: any;
  expYear: any;
  cvv: any;
  bank: any;
  wallet: any;
  paymenttype: any;
  paymentResponse: any;
  displayPayResponse: any;
  customerPreviousBills: CustomersList [];
  customerBillsList: CustomersList [];
  constructor(private custInfo: CustomerslistService, private router: Router ) {
    this.customerMail = window.localStorage.getItem('customerMail');
    if (this.customerMail) {
      this.custInfo.getCustomerBills(this.customerMail)
        .subscribe((response) => {
            console.log(response);
            this.customerBillsList = response.json();
          }
        );
    }
  }

  ngOnInit() {

  }
  summary() {
    this.viewSummary = true;
    this.isPay = false;

    this.creditOrDebitnumber = null;
    this.nameOnTheCard = null;
    this.expMonth = null;
    this.expYear = null;
    this.cvv = null;
    this.bank = null;
    this.wallet = null;

    this.custInfo.customerPreviousBills(this.customerMail).
      subscribe((response) => {
      console.log(response);
        this.customerPreviousBills = response.json();
    });
  }
  viewbillslist() {
    this.viewSummary = false;
    this.isPay = false;
    this.displayPayResponse = false;

    this.creditOrDebitnumber = null;
    this.nameOnTheCard = null;
    this.expMonth = null;
    this.expYear = null;
    this.cvv = null;
    this.bank = null;
    this.wallet = null;
  }
  payBill(payMail: String, month: any, billno: any, amount: String, duedate: any) {
    this.isPay = true;
    this.creditOrDebitnumber = null;
    this.nameOnTheCard = null;
    this.expMonth = null;
    this.expYear = null;
    this.cvv = null;
    this.bank = null;
    this.wallet = null;

    this.isPayMail = payMail; this.isPaybillNo = billno;
    this.isPayAmount = amount; this.isPayMonth = month;
  }

  onSubmitThroughCard(paymentDetailsThroughCard: NgForm) {
    this.cardno = paymentDetailsThroughCard.value.creditOrDebitnumber;
    this.cvvc = paymentDetailsThroughCard.value.cvv;
    this.expmon = paymentDetailsThroughCard.value.expMonth;
    this.expye = paymentDetailsThroughCard.value.expYear;
    this.nameonca = paymentDetailsThroughCard.value.nameOnTheCard;
    this.paya = paymentDetailsThroughCard.value.payamount;
    this.payb = paymentDetailsThroughCard.value.paybillNo;
    this.payma = paymentDetailsThroughCard.value.paymail;
    this.paymo = paymentDetailsThroughCard.value.paymonth;

    if (!isNaN(Number(this.cardno)) && !isNaN(Number(this.cvvc)) && !isNaN(Number(this.expmon)) && !isNaN(Number(this.expye)) ) {
          this.custInfo.payCustomerBillThroughCard(this.payma, this.paymo, this.payb, this.paya)
            .subscribe((response) => {
                if (response === true) {
                  this.viewSummary = true;
                  this.isPay = false;
                    this.displayPayResponse = true;

                  this.creditOrDebitnumber = null;
                  this.nameOnTheCard = null;
                  this.expMonth = null;
                  this.expYear = null;
                  this.cvv = null;
                  this.bank = null;
                  this.wallet = null;

                  setTimeout(
                    function(){
                      location.reload();
                    }, 1000);

                }else {
                  alert('Already Paid!');
                  window.location.reload();
                }
                this.paymentResponse = response;
              }
            );
    }else {
      alert('Please enter a valid card number');
    }
  }

  onSubmitThroughBank(paymentDetailsThroughBank: NgForm) {
    console.log(paymentDetailsThroughBank.value);

    this.paya = paymentDetailsThroughBank.value.payamount;
    this.payb = paymentDetailsThroughBank.value.paybillNo;
    this.payma = paymentDetailsThroughBank.value.paymail;
    this.paymo = paymentDetailsThroughBank.value.paymonth;
    this.bank = paymentDetailsThroughBank.value.bank;

    this.custInfo.payCustomerBillThroughBank(this.payma, this.paymo , this.payb, this.paya, this.bank)
      .subscribe((response) => {
          if (response === true) {
            this.viewSummary = true;
            this.isPay = false;
            this.displayPayResponse = true;

            this.creditOrDebitnumber = null;
            this.nameOnTheCard = null;
            this.expMonth = null;
            this.expYear = null;
            this.cvv = null;
            this.bank = null;
            this.wallet = null;

            setTimeout(
              function(){
                location.reload();
              }, 1000);

          }else {
            alert('Already Paid!');
            window.location.reload();
          }
          this.paymentResponse = response;
        }
      );
  }
  onSubmitThroughWallet(paymentDetailsThroughWallet: NgForm) {
  console.log(paymentDetailsThroughWallet.value);
    this.paya = paymentDetailsThroughWallet.value.payamount;
    this.payb = paymentDetailsThroughWallet.value.paybillNo;
    this.payma = paymentDetailsThroughWallet.value.paymail;
    this.paymo = paymentDetailsThroughWallet.value.paymonth;
    this.wallet = paymentDetailsThroughWallet.value.wallet;

    this.custInfo.payCustomerBillThroughWallet(this.payma, this.paymo , this.payb, this.paya, this.wallet)
      .subscribe((response) => {
          if (response === true) {
            this.viewSummary = true;
            this.isPay = false;
            this.displayPayResponse = true;

            this.creditOrDebitnumber = null;
            this.nameOnTheCard = null;
            this.expMonth = null;
            this.expYear = null;
            this.cvv = null;
            this.bank = null;
            this.wallet = null;

            setTimeout(
              function () {
                location.reload();
              }, 1000);

          } else {
            alert('Already Paid!');
            window.location.reload();
          }
          this.paymentResponse = response;
        }
      );
  }
}
