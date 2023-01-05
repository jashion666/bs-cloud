import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ActivatedRoute, ActivationStart, NavigationEnd, Router} from '@angular/router';
import {PlatformLocation} from '@angular/common';
import {NzModalService} from 'ng-zorro-antd/modal';
import {NzMessageService} from 'ng-zorro-antd/message';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-sidbar',
  templateUrl: './sidbar.component.html',
  styleUrls: ['./sidbar.component.scss']
})
export class SidbarComponent implements OnInit {

  @Input() isCollapsed: boolean;
  @Output() routerToggleEvent: EventEmitter<boolean> = new EventEmitter<boolean>();
  routerToggle = true;
  menus = [];
  noHeader = false;

  constructor(private router: Router,
              private activeRoute: ActivatedRoute,
              private location: PlatformLocation,
              private modal: NzModalService,
              private message: NzMessageService,
              private httpClient: HttpClient) {
  }

  ngOnInit(): void {
    this.routerSubscribe();
    // const currentUrl = this.location.hash.replace('#', '');
  }


  /**
   * 处理菜单状态
   * @param url url
   */
  handleMenuRoutStatus(url): void {
    this.routerToggle = !this.routerToggle;
    this.routerToggleEvent.emit(this.routerToggle);
    // 默认选中控制面板
    const router = url === '/' ? '/dashboard' : url;
    this.setRoutingStatus(router, this.menus);
  }

  routerSubscribe(): void {
    this.router.events.subscribe(event => {
      if (event instanceof ActivationStart) {
        this.noHeader = event.snapshot.data.noHeader;
      }
      if (event instanceof NavigationEnd) {
        this.handleMenuRoutStatus(event.url);
      }
    });
  }


  setRoutingStatus(url, menus: any[]): boolean {
    for (const menu of menus) {
      if (url.indexOf(menu.uri) !== -1) {
        menu.checked = true;
        return true;
      }
      // 有子菜单递归
      if (menu.children && menu.children.length > 0) {
        const checked = this.setRoutingStatus(url, menu.children);
        if (checked) {
          menu.open = true;
          return checked;
        }
      }
    }
  }


  /**
   * 查询
   */
  findRoleAndMenu() {
    // 判断是否查询隐藏hidden
    return this.httpClient.get<any>(`api/account/role`).toPromise();
  }


  /**
   * 递归添加level删除空的children
   * @param menus menu
   * @param level level
   */
  menuRecursive(menus: any[], level) {
    if (!menus || !menus.length) {
      return;
    }
    menus.forEach(item => {
        item.level = level;
        if (item.children && item.children.length > 0) {
          this.menuRecursive(item.children, level + 1);
        }
      }
    );
  }

  /**
   * 排序函数
   * @param property property
   */
  compare(property) {
    return function(a, b) {
      const value1 = a[property];
      const value2 = b[property];
      return value1 - value2;
    };
  }


}
