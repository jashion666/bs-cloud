import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {slideInAnimation} from '../../animations';
import {Router} from '@angular/router';
import {PrincipalService} from 'src/app/core/auth/principal.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
  animations: [
    slideInAnimation
  ]
})
export class NavbarComponent implements OnInit, OnDestroy {

  isCollapsed: boolean;
  userInfo: any;
  routerToggle: boolean;

  constructor(private router: Router, private principalService: PrincipalService) {

  }

  ngOnInit(): void {
    this.userInfo = this.principalService.getIdentity();
  }

  ngOnDestroy(): void {
    this.router.events.subscribe().unsubscribe();
  }


  handleRouterToggle(routerToggle: boolean) {
    this.routerToggle = routerToggle;
  }

  handleCollapsedToggle($event: boolean) {
    this.isCollapsed = $event;
  }

}
