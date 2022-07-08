import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {LoginRegisterService} from "../service/login-register.service";
import {UserRegisterDTO} from "../../definitions/objects";
import {Router} from "@angular/router";
import {crossFieldValidator} from "../../../library/custom-validators/crossField.validator";
import {HttpErrorResponse} from "@angular/common/http";
import {ErrorService} from "../../../library/error-handling/error.service";
import {MatDialog} from "@angular/material/dialog";
import {EmailConfirmationComponent} from "../email-confirmation/email-confirmation.component";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  registerFormGroup: FormGroup;
  userRegisterDTO: UserRegisterDTO = <UserRegisterDTO>{};

  constructor(private loginRegisterService: LoginRegisterService, private router: Router, private fb: FormBuilder, private errorHandling: ErrorService,
              private dialog: MatDialog) {

  }

  ngOnInit(): void {
    this.registerFormGroup = this.fb.group({
      "gender": new FormControl('', [Validators.required]),
      "firstname": new FormControl('', [Validators.required, Validators.minLength(2)]),
      "lastname": new FormControl('', [Validators.required, Validators.minLength(2)]),
      "birthday": new FormControl(new Date()),
      "email": new FormControl('', [Validators.required, Validators.pattern(this.loginRegisterService.regex.email)]),
      "password": new FormControl('', [Validators.required, Validators.pattern(this.loginRegisterService.regex.passwort)]),
      "confirmPassword": new FormControl('', [Validators.required]),
      "unrequired": new FormControl()
    }, {
      validator: crossFieldValidator('password', 'confirmPassword')
    });

  }

  register() {
    this.loginRegisterService.registerNewUser(this.userRegisterDTO).toPromise().then((data) => {
      const dialogRef = this.dialog.open(EmailConfirmationComponent);
      dialogRef.afterClosed().subscribe(result => {
        if(result == true){
          this.router.navigateByUrl('/user/login');
        }
      });
    }).catch((error: HttpErrorResponse) => {
      this.errorHandling.openInformation("Diese Email-Adresse existiert bereits, bitte verwenden Sie eine andere Email-Adresse");
    });
  }
}


