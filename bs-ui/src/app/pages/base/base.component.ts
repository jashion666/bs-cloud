import {FormErrorMessageService} from '../../service/form-error-message.service';
import {ServiceLocator} from '../../service/service.locator';
import {NzMessageService} from 'ng-zorro-antd/message';
import {GlobalService} from '../../service/global.service';

export class BaseComponent {
  public fms: FormErrorMessageService;
  public messageService: NzMessageService;
  public globalService: GlobalService

  constructor() {
    this.fms = ServiceLocator.injector.get(FormErrorMessageService);
    this.messageService = ServiceLocator.injector.get(NzMessageService);
    this.globalService = ServiceLocator.injector.get(GlobalService);
  }

  public showSpinning() {
    this.globalService.showSpinning();
  }

  public closeSpinning() {
    this.globalService.closeSpinning();
  }
}
