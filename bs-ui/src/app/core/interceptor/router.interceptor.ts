import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {PrincipalService} from '../auth/principal.service';
import {NzMessageService} from 'ng-zorro-antd/message';

@Injectable({
  providedIn: 'root',
})
export class RouterInterceptor implements CanActivate {


  constructor(private principalService: PrincipalService, private router: Router,
              private message: NzMessageService) {
  }

  canActivate(route: ActivatedRouteSnapshot,
              state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    if (!this.checkToken()) {
      return false;
    }
    const url = state.url;
    return this.checkIdentity(url);
  }

  private checkToken() {
    if (!this.principalService.hasToken()) {
      this.router.navigate(['/login'], {
        skipLocationChange: false,
      }).then(() => {
        this.message.error('身份认证失败，请重新登陆');
      });
      return false;
    }
    return true;
  }

  private checkIdentity(url) {
    return this.principalService.identity().then(ret => {
      if (!ret) {
        this.message.error('无效的认证信息，请重新登陆');
        return false;
      }
      if (!url || url === '/') {
        return true;
      }
      return true;
    });
  }
}
