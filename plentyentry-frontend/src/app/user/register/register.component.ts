import {Component, OnInit} from '@angular/core';
import {
  FormControl,
  FormBuilder,
  Validators,
  FormGroup
} from "@angular/forms";
import {LoginRegisterService} from "../service/login-register.service";
import {UserRegisterDTO} from "../../definitions/objects";
import {Router} from "@angular/router";
import {crossFieldValidator} from "../../../library/custom-validators/crossField.validator";
import {Errormesages} from "../service/login-register.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  registerFormGroup: FormGroup;
  userRegisterDTO: UserRegisterDTO = <UserRegisterDTO>{};
  errorMessages: Errormesages;

  constructor(private loginRegisterService: LoginRegisterService, private router: Router, private fb: FormBuilder) {

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
      this.router.navigateByUrl('/user/login');
    })
  }
}


