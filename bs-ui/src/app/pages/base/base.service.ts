import {HttpClient} from '@angular/common/http';
import {ServiceLocator} from '../../service/service.locator';

export class BaseService {
  protected http: HttpClient

  constructor() {
    this.http = ServiceLocator.injector.get(HttpClient);
  }

}
