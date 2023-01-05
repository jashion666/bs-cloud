import {Injectable, OnDestroy} from '@angular/core';

@Injectable({providedIn: 'root'})
export class FormErrorMessageService implements OnDestroy {

  /**
   * storage form error message.  [s: string] is form's field, string is type
   * @private
   */
  private errorMessage: { [s: string]: string };

  constructor() {
    this.errorMessage = {};
  }

  public clear(): void {
    this.errorMessage = {};
  }

  public error(k, v): void {
    this.errorMessage[k] = v;
    this.errorMessage[k + 'State'] = 'error';
  }

  public msg(k): string {
    if (this.errorMessage.hasOwnProperty(k)) {
      return this.errorMessage[k];
    }
    return null;
  }

  public state(k): string {
    if (this.errorMessage.hasOwnProperty(k + 'State')) {
      return this.errorMessage[k + 'State'];
    }
    return 'success';
  }

  ngOnDestroy(): void {
    this.clear();
  }

}
