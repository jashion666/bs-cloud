import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {PrincipalService} from './principal.service';
import {AccountService} from './account.service';
import {SessionStorageService} from 'ngx-webstorage';
import {AuthService} from './auth.service';
import {ResponseEnum} from '../../response.enum';

@Injectable({providedIn: 'root'})
export class LoginService {

  constructor(private principal: PrincipalService,
              private authJwtService: AuthService,
              private http: HttpClient,
              private sessionStorageService: SessionStorageService,
              private accountService: AccountService) {
  }

  login(credentials: any): Promise<any> {
    return this.authJwtService.login(credentials).then(result => {
      if (result.body.code !== ResponseEnum.SUCCESS) {
        return result;
      }
      return this.principal.identity(true).then(() => {
        return result;
      });
    });
  }

  logout() {
    return new Promise((resolve, reject) => {
      this.accountService.logout().then(result => {
        if (result.body.code !== ResponseEnum.SUCCESS) {
          reject(result.body.msg);
          return;
        }
        this.authJwtService.logout().subscribe();
        this.principal.logout();
        resolve();
      });
    });
  }

}
