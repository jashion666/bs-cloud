import {Injector} from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpErrorResponse
} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {FormErrorMessageService} from '../../service/form-error-message.service';
import {environment} from '../../../environments/environment';

export class FormInterceptor implements HttpInterceptor {

  constructor(private injector: Injector) {
  }

  /**
   * Form Interceptor
   */
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<any> {

    const formMessageService: FormErrorMessageService = this.injector.get(FormErrorMessageService);
    formMessageService.clear();
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === environment.formDataInvalidCode) {
          const msgList = error.error.data;
          msgList.forEach(item => {
            formMessageService.error(item.name, item.message);
          });
          return throwError({code: error.status});
        }
        return throwError(error.message);
      }),
    );
  }
}
