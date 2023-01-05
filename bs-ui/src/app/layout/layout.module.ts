import {NgModule} from '@angular/core';
import {SharedModule} from '../shared/shared.module';
import {NavbarComponent} from './navbar/navbar.component';
import {NzMenuModule} from 'ng-zorro-antd/menu';
import {NzDropDownModule} from 'ng-zorro-antd/dropdown';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AppRoutingModule} from '../app-routing.module';
import {NzMessageService} from 'ng-zorro-antd/message';
import {NzNotificationService} from 'ng-zorro-antd/notification';
import {NzModalService} from 'ng-zorro-antd/modal';
import {SidbarComponent} from './sidbar/sidbar.component';
import {HeaderComponent} from './header/header.component';
import {NzSpinModule} from 'ng-zorro-antd/spin';
import {NotFoundComponent} from './not-found/not-found.component';
import {NzResultModule} from 'ng-zorro-antd/result';
import {NzButtonModule} from 'ng-zorro-antd/button';


@NgModule({
  declarations: [NavbarComponent, SidbarComponent, HeaderComponent, NotFoundComponent],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    SharedModule,
    NzMenuModule,
    NzDropDownModule,
    AppRoutingModule,
    NzSpinModule,
    NzResultModule,
    NzButtonModule
  ],
  providers: [
    NzMessageService,
    NzNotificationService,
    NzModalService
  ]
})
export class LayoutModule {
}
