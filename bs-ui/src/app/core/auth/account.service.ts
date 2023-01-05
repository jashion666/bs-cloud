import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';

@Injectable({providedIn: 'root'})
export class AccountService {
  constructor(private http: HttpClient) {
  }

  /**
   * account
   */
  account(): Promise<any> {
    return this.http.get(`${environment.api}/auth/account`, {observe: 'response'}).toPromise();
  }

  /**
   * logout
   */
  logout(): Promise<any> {
    return this.http.get(`${environment.api}/auth/logout`,  {observe: 'response'}).toPromise();
  }

}
