import {Injector} from '@angular/core';
import {NzMessageService} from 'ng-zorro-antd/message';

export class ServiceLocator {
  static injector: Injector;
  static messageService: NzMessageService;
}
