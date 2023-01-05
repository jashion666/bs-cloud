import {Injectable} from '@angular/core';
import {LocalStorageService, SessionStorageService} from 'ngx-webstorage';
import {AccountService} from './account.service';
import {ResponseEnum} from '../../response.enum';

@Injectable({providedIn: 'root'})
export class PrincipalService {

  private authenticated = false;
  private userIdentity: any;
  private permissions: any[];

  constructor(private accountService: AccountService,
              private localStorage: LocalStorageService,
              private sessionStorage: SessionStorageService) {
  }

  /**
   * getIdentity
   */
  getIdentity() {
    return this.userIdentity;
  }

  /**
   * getPermissions
   */
  getPermissions() {
    return this.permissions;
  }

  has(perm) {
    return this.admin() || this.permissions.includes(perm);
  }

  identity(force?: boolean): Promise<any> {
    if (force === true) {
      this.userIdentity = undefined;
    }

    if (this.userIdentity) {
      return Promise.resolve(this.userIdentity);
    }

    return this.accountService.account().then(response => {
      if (response.body && response.body.state !== ResponseEnum.FAILED) {
        this.userIdentity = response.body.data;
        this.permissions = response.body.data;
        this.authenticated = true;
      } else {
        this.userIdentity = null;
        this.authenticated = false;
        this.permissions = null;
      }
      return this.userIdentity;
    }).catch((e) => {
      console.error(e);
      this.userIdentity = null;
      this.authenticated = false;
      this.permissions = null;
      return null;
    });
  }

  hasToken() {
    return this.getToken();
  }

  admin() {
    return this.userIdentity.admin;
  }

  getToken() {
    return this.localStorage.retrieve('authenticationToken') || this.sessionStorage.retrieve('authenticationToken');
  }

  logout() {
    this.authenticated = false;
    this.userIdentity = null;
  }
}
