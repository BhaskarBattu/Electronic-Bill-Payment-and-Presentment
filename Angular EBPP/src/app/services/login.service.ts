import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Headers} from '@angular/http';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {BaseUrl} from '../model/baseUrl';
@Injectable()
export class LoginService {
  // baseUrl: any= 'http://192.168.35.61:8080/EBPP-0.0.1-SNAPSHOT';
  // baseUrl: any= 'http://localhost:8080/EBPP-0.0.1-SNAPSHOT';
   baseUrl = BaseUrl.baseUrlID;
  constructor(private http: Http) { }

  public loginWithOAuth(displayname: any, email: any) {
    const loginDetails = {
      'username': displayname,
      'email': email
    };
    const headers = new Headers();
    headers.append('Content-Type',
      'application/json');
    return this.http.post(this.baseUrl + '/customer/login',
      loginDetails, { headers: headers, withCredentials: true });
  }
}

