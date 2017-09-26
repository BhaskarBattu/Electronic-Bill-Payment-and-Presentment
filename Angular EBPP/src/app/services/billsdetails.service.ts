import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Headers} from '@angular/http';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {BaseUrl} from '../model/baseUrl';
import 'rxjs/add/operator/map';
@Injectable()
export class BillsdetailsService {
  cosntd: any;
  // baseUrl: any= 'http://192.168.35.61:8080/EBPP-0.0.1-SNAPSHOT';
  baseUrl = BaseUrl.baseUrlID;
  constructor(private http: Http) { }

  public uploadBillsDetails(details: any) {
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this.http.post(this.baseUrl + '/biller/addBillsDetails',
      details, { headers: headers, withCredentials: true });
  }
  public getBills(start, end) {
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this.http.get(this.baseUrl + `/biller/getBills/?start=${start}&end=${end}`,
      { headers: headers, withCredentials: true });
  }
  public searchBills(term: string) {
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this.http.get(this.baseUrl + `/biller/getSearchBills/?name=${term}`,
      { headers: headers, withCredentials: true });
  }
  public searchBillsBasedonDate(startDate, endDate) {
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    const searchBasedOnDate = {
      'startDate': startDate,
      'endDate': endDate,
    };
    return this.http.post(this.baseUrl + '/biller/getSearchBillsBasedonDate', searchBasedOnDate,
      { headers: headers, withCredentials: true });
  }

  public getCountofCustomersBillsList(){
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this.http.get(this.baseUrl + '/biller/getpaginationCountOfCustomersTotalBills',
      { headers: headers, withCredentials: true }).map(response => response.json());
  }
  getPaginationArray(CustomersBillsCount) {

    const paginationArray = [];
    let x: number;
    x =  parseInt(CustomersBillsCount, 10);
    const val = 3;
    let noOfPages: number =  parseInt((x / 3 ) + '', 10);
    if (x % val) {
      noOfPages = noOfPages + 1;
    }

    while (paginationArray.length < noOfPages) {
      paginationArray.push( paginationArray.length + 1 );
    }
    return paginationArray;

  }

}

