import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {NzNotificationService} from 'ng-zorro-antd/notification';
import {Injector} from '@angular/core';
import {Router} from '@angular/router';
import {PrincipalService} from '../auth/principal.service';
import {AuthService} from '../auth/auth.service';
import {environment} from '../../../environments/environment';
import {ResponseEnum} from '../../response.enum';

export class ExceptionHandlerInterceptor implements HttpInterceptor {

  constructor(private injector: Injector) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      tap(
        (res) => {
          if (res instanceof HttpResponse) {
            const tokenRefresh = res.headers.get('Token-Refresh');
            // handler token refresh
            if (tokenRefresh && ResponseEnum.TOKEN_REFRESH === parseInt(tokenRefresh, 10)) {
              this.refreshToken(res);
              return;
            }
            // handler token expire
            if (res.body.code === ResponseEnum.TOKEN_EXPIRE) {
              this.handlerTokenExpire();
              return;
            }
          }
        },
        (err: any) => {
          if (err instanceof HttpErrorResponse) {
            if (err.status === environment.formDataInvalidCode) {
              return;
            }
            if (this.is4xxClientError(err.status)) {
              this.handler4xxError(err);
            } else {
              this.notifyErrorToUser(err.status);
            }
          } else {
            this.notifyErrorToUser('-999');
          }
        }),
    );
  }

  /**
   * handler response status is 4xx error
   * @param err
   * @private
   */
  private handler4xxError(err) {
    const authJwtService: AuthService = this.injector.get(AuthService);
    const principal: PrincipalService = this.injector.get(PrincipalService);
    authJwtService.logout().subscribe();
    principal.logout();
    const router: Router = this.injector.get(Router);
    router.navigate(['/login'], {
      skipLocationChange: false,
    }).then(() => {
      this.notifyErrorToUserWithContent(err.error.message);
    });
  }

  /**
   * judge is 4xx error
   * @param status
   * @private
   */
  private is4xxClientError(status): boolean {
    const seriesCode = Math.floor(status / 100);
    return seriesCode === 4;
  }

  /**
   * notify Error To User
   * @param status
   * @private
   */
  private notifyErrorToUser(status) {
    const content = '系统错误，Code:' + status;
    const notification: NzNotificationService = this.injector.get(NzNotificationService);
    notification.create(
      'error',
      '系统错误',
      content
    );
  }

  /**
   * notify Error To User With Content
   * @param content
   * @private
   */
  private notifyErrorToUserWithContent(content) {
    const notification: NzNotificationService = this.injector.get(NzNotificationService);
    notification.create(
      'error',
      '系统错误',
      content
    );
  }

  private refreshToken(res: HttpResponse<any>) {
    const authJwtService: AuthService = this.injector.get(AuthService);
    const token = res.headers.get(environment.tokenHeader);
    authJwtService.refreshToken(token);
  }

  private handlerTokenExpire() {
    const authJwtService: AuthService = this.injector.get(AuthService);
    const principal: PrincipalService = this.injector.get(PrincipalService);
    authJwtService.logout().subscribe();
    principal.logout();
    const router: Router = this.injector.get(Router);
    router.navigate(['/login'], {
      skipLocationChange: false,
    });
  }
}
