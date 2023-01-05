import {BrowserModule} from '@angular/platform-browser';
import {ErrorHandler, Injector, NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {registerLocaleData} from '@angular/common';
import {NzMenuModule} from 'ng-zorro-antd/menu';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NZ_I18N} from 'ng-zorro-antd/i18n';
import {environment} from '../environments/environment';
import {LayoutModule} from './layout/layout.module';
import {SharedModule} from './shared/shared.module';
import {NgProgressModule} from 'ngx-progressbar';
import {LoginComponent} from './pages/login/login.component';
import {NzFormModule} from 'ng-zorro-antd/form';
import {NzInputModule} from 'ng-zorro-antd/input';
import {NzTypographyModule} from 'ng-zorro-antd/typography';
import {NzCheckboxModule} from 'ng-zorro-antd/checkbox';
import {NzButtonModule} from 'ng-zorro-antd/button';
import {LocalStorageService, NgxWebstorageModule, SessionStorageService} from 'ngx-webstorage';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {FormInterceptor} from './core/interceptor/form.interceptor';
import {AuthInterceptor} from './core/interceptor/auth.interceptor';
import {ExceptionHandlerInterceptor} from './core/interceptor/exception.handler.interceptor';
import {NzMessageService} from 'ng-zorro-antd/message';
import {NzNotificationService} from 'ng-zorro-antd/notification';
import {NgProgressHttpModule} from 'ngx-progressbar/http';
import {ServiceLocator} from './service/service.locator';
import {GlobalErrorHandler} from './exception/global-error-handler';

registerLocaleData(environment.locale);

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NzMenuModule,
    BrowserAnimationsModule,
    LayoutModule,
    SharedModule,
    NgProgressHttpModule,
    NgProgressModule,
    NgProgressModule.withConfig({
      spinnerPosition: 'left',
      color: '#189cf5'
    }),
    NzFormModule,
    NzInputModule,
    NzTypographyModule,
    NzCheckboxModule,
    NzButtonModule,
    NgxWebstorageModule.forRoot(),
  ],
  providers: [
    {provide: NZ_I18N, useValue: environment.i18n},
    NzMessageService,
    NzNotificationService,
    {
      provide: ErrorHandler,
      useClass: GlobalErrorHandler,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: FormInterceptor, multi: true,
      deps: [Injector]
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
      deps: [LocalStorageService, SessionStorageService]
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ExceptionHandlerInterceptor,
      multi: true,
      deps: [Injector]
    },

  ],
  bootstrap: [AppComponent]
})
export class AppModule {

  constructor(private injector: Injector, private messageService: NzMessageService) {
    ServiceLocator.injector = injector;
    ServiceLocator.messageService = messageService;
  }
}
