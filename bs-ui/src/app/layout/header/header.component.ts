import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {LoginService} from '../../core/auth/login.service';
import {Router} from '@angular/router';
import {NzMessageService} from 'ng-zorro-antd/message';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  @Input() isCollapsed: boolean;
  @Input() userInfo: any;
  @Output() collapsedToggleEvent: EventEmitter<boolean> = new EventEmitter<boolean>();

  constructor(private loginService: LoginService, private router: Router, private message: NzMessageService) {
  }

  ngOnInit(): void {
  }

  toggleCollapsed(): void {
    this.isCollapsed = !this.isCollapsed;
    this.collapsedToggleEvent.emit(this.isCollapsed);
  }

  /**
   * logout
   */
  logout(): void {
    this.loginService.logout().then(() => {
      this.router.navigate(['/login']).then(() => {
        this.message.success('退出成功');
      });
    });
  }

}
