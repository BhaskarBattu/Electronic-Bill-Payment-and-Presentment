import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Headers} from '@angular/http';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {Observable} from 'rxjs/Observable';
import {CustomersList} from '../model/CustomersList';
import {BillsList} from '../model/billsList';
import {BaseUrl} from '../model/baseUrl';

@Injectable()
export class CustomerslistService {
  // baseUrl: any= 'http://192.168.35.61:8080/EBPP-0.0.1-SNAPSHOT';
  baseUrl = BaseUrl.baseUrlID;
  constructor(private http: Http) { }

  public uploadCustomersList(details: any) {
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this.http.post(this.baseUrl + '/biller/addCustomers',
      details, { headers: headers, withCredentials: true });
  }


  public getCountofCustomersBillsList(){
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this.http.get(this.baseUrl + '/biller/getpaginationCountOfCustomerBills',
      { headers: headers, withCredentials: true }).map(response => response.json());
  }

  getPaginationArray(CustomersBillsCount) {

    const paginationArray = [];
    let x: number;
    x =  parseInt(CustomersBillsCount, 10);
    const val = 3
    let noOfPages: number =  parseInt((x / 3 ) + '', 10);
    if (x % val) {
      noOfPages = noOfPages + 1;
    }

    while (paginationArray.length < noOfPages) {
      paginationArray.push( paginationArray.length + 1 );
    }
    return paginationArray;

  }

  public getCountofCustomersList(){
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this.http.get(this.baseUrl + '/biller/getpaginationCountOfCustomers',
      { headers: headers, withCredentials: true }).map(response => response.json());
  }
















  public uploadDataSource(payload): Observable<any[]> {
    const headers = new Headers();
    return this.http.post(this.baseUrl + '/biller/uploadCustomersList',
      payload, { headers: headers, withCredentials: true }).map(response => response.json());
  }

  /*public customersList() {
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this.http.get(this.baseUrl + '/biller/getCustomers', { headers: headers, withCredentials: true });
  }*/

  public customersList(start, end) {
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this.http.get(this.baseUrl + `/biller/getCustomers/?start=${start}&end=${end}`, { headers: headers, withCredentials: true });
  }

  public customersListToView(start, end) {
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this.http.get(this.baseUrl + `/biller/getCustomersToView?start=${start}&end=${end}`,
      { headers: headers, withCredentials: true });
  }

  public searchCustomers(term: string) {
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this.http.get(this.baseUrl + `/biller/getSearchCustomers/?name=${term}`,
      { headers: headers, withCredentials: true });
  }

    public getCustomerBills(custMail: any) {
      const headers = new Headers();
      headers.append('Content-Type',
        'application/json');
          const custInfo = {
            'email': custMail
          };
      return this.http.post(this.baseUrl + '/customer/customerBills', custInfo,
        { headers: headers, withCredentials: true });
    }
    public payCustomerBillThroughCard(mail: any, month: any, billno: any, amount: any) {
      const headers = new Headers();
      headers.append('Content-Type',
        'application/json');
      const payInfo = {
        'email' : mail,
        'month' : month,
        'billno' : billno,
        'amount' : amount,
        'paymentType' : 'card'
      };
      return this.http.post(this.baseUrl + '/customer/payBillThroughCard', payInfo,
        { headers: headers, withCredentials: true } ).map(response => response.json());
    }
  public payCustomerBillThroughBank(mail: any, month: any, billno: any, amount: any, online: any) {
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    const payInfo = {
      'email' : mail,
      'month' : month,
      'billno' : billno,
      'amount' : amount,
      'paymentType': 'Net banking',
      'paymentOption' : online
    };
    return this.http.post(this.baseUrl + '/customer/payBill', payInfo,
      { headers: headers, withCredentials: true } ).map(response => response.json());
  }
  public payCustomerBillThroughWallet(mail: any, month: any, billno: any, amount: any, online: any) {
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    const payInfo = {
      'email' : mail,
      'month' : month,
      'billno' : billno,
      'amount' : amount,
      'paymentType': 'Wallet',
      'paymentOption' : online
    };
    return this.http.post(this.baseUrl + '/customer/payBill', payInfo,
      { headers: headers, withCredentials: true } ).map(response => response.json());
  }

  public customerPreviousBills(customermail: any) {
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this.http.get(this.baseUrl + `/customer/getCustomerPreviousBills/?email=${customermail}`,
      { headers: headers, withCredentials: true });
  }
}

