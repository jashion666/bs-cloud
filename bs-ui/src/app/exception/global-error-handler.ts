import {ErrorHandler, Injectable} from '@angular/core';
import {ExceptionHandler} from './exception-handler.';
import {GlobalService} from '../service/global.service';

@Injectable()
export class GlobalErrorHandler implements ErrorHandler {

  constructor(private globalService: GlobalService) {
  }

  handleError(error: Error) {
    this.globalService.closeSpinning();
    ExceptionHandler.handler417UnCaughtException(error);
  }
}
