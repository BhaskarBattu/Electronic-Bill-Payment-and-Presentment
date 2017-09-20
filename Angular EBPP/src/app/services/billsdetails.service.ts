import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Headers} from '@angular/http';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';

@Injectable()
export class BillsdetailsService {
  cosntd: any;
  // baseUrl: any= 'http://192.168.35.61:8080/EBPP-0.0.1-SNAPSHOT';
  baseUrl: any= 'http://localhost:8080/EBPP-0.0.1-SNAPSHOT';
  constructor(private http: Http) { }

  public uploadBillsDetails(details: any) {
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this.http.post(this.baseUrl + '/biller/addBillsDetails',
      details, { headers: headers, withCredentials: true });
  }
  public getBills() {
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this.http.get(this.baseUrl + '/biller/getBills',
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
}

