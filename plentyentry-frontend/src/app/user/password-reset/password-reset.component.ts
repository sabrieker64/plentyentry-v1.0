import {Component, OnInit} from '@angular/core';
import {UserAuthReqDTO} from "../../definitions/objects";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {LoginRegisterService} from "../service/login-register.service";
import {ErrorService} from "../../../library/error-handling/error.service";

@Component({
  selector: 'app-password-reset',
  templateUrl: './password-reset.component.html',
  styleUrls: ['./password-reset.component.scss']
})
export class PasswordResetComponent implements OnInit {

  userAuthReqDTO: UserAuthReqDTO = <UserAuthReqDTO>{};
  resetPasswordFormGroup: FormGroup;
  passwordResetStatus = "";

  constructor(private router: Router, private fb: FormBuilder, private errorHandling: ErrorService, private loginRegisterService: LoginRegisterService) {
  }

  ngOnInit(): void {

    this.resetPasswordFormGroup = this.fb.group({
      "email": new FormControl('', [Validators.required, Validators.pattern(this.loginRegisterService.regex.email)]),
    });

  }

  passwordReset() {

    this.userAuthReqDTO.password = "";

    this.loginRegisterService.resetPassword(this.userAuthReqDTO).toPromise().then(
      (user) => {
        if (user.email != '') {
          this.passwordResetStatus = 'Password erfolgreich zurückgesetzt, Bitte Überprüfen Sie Ihre Email für Ihr neues Passwort!';
          localStorage.setItem('token', 'No token');
        } else {
          this.passwordResetStatus = 'Es ist ein Fehler unterlaufen';
        }
      },
      (err) => {
        this.errorHandling.openErrorBox(err);
      }
    )
  }

}
