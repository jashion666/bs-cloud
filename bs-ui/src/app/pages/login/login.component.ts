import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {NzMessageService} from 'ng-zorro-antd/message';
import {ResponseEnum} from '../../response.enum';
import {LoginService} from '../../core/auth/login.service';
import {BaseComponent} from '../base/base.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent extends BaseComponent implements OnInit {

  userNamePasswordForm!: FormGroup;
  loading = false;

  constructor(private fb: FormBuilder,
              private router: Router,
              private loginService: LoginService,
              private message: NzMessageService) {
    super();
    this.userNamePasswordForm = this.fb.group({
      username: [null, [Validators.required, Validators.email]],
      password: [null],
      rememberMe: [true]
    });
    // const msg = {
    //   username: {
    //     required: '请输入用户名 ',
    //     email: '格式不对'
    //   }
    // };

    // this.userNamePasswordForm.valueChanges.pipe(
    //   debounceTime(500),
    //   distinctUntilChanged(),
    // ).subscribe((form) => {
    //   if (this.userNamePasswordForm.invalid) {
    //     console.log(this.userNamePasswordForm);
    //     for (const field of Object.keys(this.userNamePasswordForm.controls)) {
    //       const errors = this.userNamePasswordForm.controls[field].errors;
    //       if (errors) {
    //         for (const error of Object.keys(errors)) {
    //           this.fms.error(field, msg[field][error]);
    //         }
    //       }
    //     }
    //   }
    // });
  }

  ngOnInit(): void {
    this.loading = false;
  }

  /**
   * login
   */
  login() {
    this.loading = true;
    this.loginService.login(this.userNamePasswordForm.value)
      .then(data => {
        this.loading = false;
        // 认证异常
        if (data.body.code === ResponseEnum.FAILED) {
          this.message.error(data.body.msg);
          return;
        }
        this.router.navigate([''], {
          skipLocationChange: false,
        });
      }).catch((e) => {
      this.loading = false;
    });
  }

}
