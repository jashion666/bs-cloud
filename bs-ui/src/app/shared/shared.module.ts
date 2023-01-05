import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PageHeaderComponent} from './page-header/page-header.component';
import {NzPageHeaderModule} from 'ng-zorro-antd/page-header';
import {NzBreadCrumbModule} from 'ng-zorro-antd/breadcrumb';
import {BackHeaderComponent} from './back-header/back-header.component';
import {NzGridModule} from 'ng-zorro-antd/grid';
import {IconsProviderModule} from '../icons-provider.module';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NzLayoutModule} from 'ng-zorro-antd/layout';

@NgModule({
  declarations: [PageHeaderComponent, BackHeaderComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NzLayoutModule,
    NzPageHeaderModule,
    NzBreadCrumbModule,
    NzGridModule,
    IconsProviderModule,
  ],
  exports: [
    PageHeaderComponent,
    BackHeaderComponent,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NzLayoutModule,
    NzPageHeaderModule,
    NzBreadCrumbModule,
    NzGridModule,
    IconsProviderModule
  ]
})
export class SharedModule {
}
