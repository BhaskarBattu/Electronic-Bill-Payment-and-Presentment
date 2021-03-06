import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Headers} from '@angular/http';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {BaseUrl} from '../model/baseUrl';

@Injectable()
export class LogoutService {
  // baseUrl: any= 'http://192.168.35.61:8080/EBPP-0.0.1-SNAPSHOT';
  baseUrl = BaseUrl.baseUrlID;
  constructor(private http: Http) { }

  public logout() {
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this.http.get(this.baseUrl + '/customer/invalidateSession', { headers: headers, withCredentials: true });
  }
}

