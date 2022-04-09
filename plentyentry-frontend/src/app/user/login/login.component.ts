import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {LoginRegisterService} from "../service/login-register.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {UserAuthReqDTO} from "../../definitions/objects";
import {HttpErrorResponse} from "@angular/common/http";
import {ErrorService} from "../../../library/error-handling/error.service";

@Component({
  selector: 'app-login-register',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent implements OnInit {
  userAuthReqDTO: UserAuthReqDTO = <UserAuthReqDTO>{};
  loginFormGroup: FormGroup;

  constructor(private router: Router, private loginRegisterService: LoginRegisterService, private fb: FormBuilder, private errorHandling: ErrorService) {
  }

  ngOnInit(): void {

    this.loginFormGroup = this.fb.group({
      "email": new FormControl('', [Validators.required, Validators.pattern(this.loginRegisterService.regex.email)]),
      "password": new FormControl('', [Validators.required, Validators.pattern(this.loginRegisterService.regex.passwort)])
    });
  }

  authenticate() {
    this.loginRegisterService.authenticateUser(this.userAuthReqDTO).toPromise().then((userDTO) => {
      localStorage.setItem('token', userDTO.jwtToken);
      this.router.navigateByUrl('/event/overview');
    }).catch((error: HttpErrorResponse) => {
      this.errorHandling.openErrorBox(error.message);
    })
  }
}
