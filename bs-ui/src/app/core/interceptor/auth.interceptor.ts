import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {LocalStorageService, SessionStorageService} from 'ngx-webstorage';
import {environment} from '../../../environments/environment';

export class AuthInterceptor implements HttpInterceptor {

  constructor(private localStorage: LocalStorageService, private sessionStorage: SessionStorageService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (!request || !request.url || (/^http/.test(request.url)) || request.url.startsWith(`${environment.api}/authentication`)) {
      return next.handle(request);
    }
    const token = this.localStorage.retrieve('authenticationToken') || this.sessionStorage.retrieve('authenticationToken');
    const header = '{"' + environment.tokenHeader + '":"' + token + '"}';
    if (!!token) {
      request = request.clone({
        setHeaders: JSON.parse(header)
      });
    }
    return next.handle(request);
  }

}
