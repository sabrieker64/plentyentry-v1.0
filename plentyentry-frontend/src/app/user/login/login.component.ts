import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {LoginRegisterService} from "../service/login-register.service";

@Component({
  selector: 'app-login-register',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent implements OnInit {
  usernameValue = '';
  passwordValue = '';

  constructor(private router: Router, private loginRegisterService: LoginRegisterService) {
  }

  ngOnInit(): void {

  }

  login(username:string, password:string) {
    this.loginRegisterService.login(username, password);
  }

  openRegisterView() {
    return this.router.navigateByUrl('/user/register');
  }
}
