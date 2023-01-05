import {Injectable} from '@angular/core';

@Injectable({providedIn: 'root'})
export class GlobalService {

  private spinning: boolean;

  public isLoading() {
    return this.spinning;
  }

  public showSpinning() {
    this.spinning = true;
  }

  public closeSpinning() {
    this.spinning = false;
  }

}
