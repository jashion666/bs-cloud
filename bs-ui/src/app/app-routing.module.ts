import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {NavbarComponent} from './layout/navbar/navbar.component';
import {LoginComponent} from './pages/login/login.component';
import {RouterInterceptor} from './core/interceptor/router.interceptor';
import {NotFoundComponent} from './layout/not-found/not-found.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    // after login
    path: '',
    component: NavbarComponent,
    canActivate: [RouterInterceptor],
    children: []
  },
  {
    path: '**',
    component: NotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
