import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {LocalStorageService, SessionStorageService} from 'ngx-webstorage';
import {map} from 'rxjs/operators';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';

@Injectable({providedIn: 'root'})
export class AuthService {

  constructor(private http: HttpClient,
              private sessionStorage: SessionStorageService,
              private localStorage: LocalStorageService) {
  }

  login(credentials: any): Promise<any> {
    const that = this;
    return this.http
      .post(`${environment.api}/auth/authentication`, credentials, {observe: 'response'})
      .pipe(map(authenticateSuccess.bind(this)))
      .toPromise();

    function authenticateSuccess(resp) {
      const bstoken = resp.headers.get(environment.tokenHeader);
      if (bstoken) {
        that.storeAuthenticationToken(bstoken, credentials.rememberMe);
      }
      return resp;
    }
  }

  /**
   * storage token
   * @param token token
   * @param rememberMe rememberMe
   */
  storeAuthenticationToken(token, rememberMe) {
    if (rememberMe) {
      this.localStorage.store('authenticationToken', token);
    } else {
      this.sessionStorage.store('authenticationToken', token);
    }
  }

  /**
   * refresh token
   * @param token token
   */
  refreshToken(token) {
    const rememberMe = !!this.localStorage.retrieve('authenticationToken');
    this.storeAuthenticationToken(token, rememberMe);
  }

  /**
   * logout
   */
  logout(): Observable<any> {
    return new Observable(observer => {
      this.localStorage.clear('authenticationToken');
      this.sessionStorage.clear('authenticationToken');
      observer.complete();
    });
  }

}
