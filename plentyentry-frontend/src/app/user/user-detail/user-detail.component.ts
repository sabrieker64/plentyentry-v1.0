import {Component, OnInit} from '@angular/core';
import {UserDTO} from "../../definitions/objects";
import {UserDetailService} from "../service/user-detail.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {LoginRegisterService} from "../service/login-register.service";
import {ActivatedRoute} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";
import {ErrorService} from "../../../library/error-handling/error.service";


@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.scss']
})
export class UserDetailComponent implements OnInit {
  registerFormGroup: FormGroup;
  userDTO: UserDTO = <UserDTO>{};
  paramUserId: number;

  constructor(private userDetailService: UserDetailService, private fb: FormBuilder,
              private loginRegisterService: LoginRegisterService, private route: ActivatedRoute, private errorHandling: ErrorService) {
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
      "street": new FormControl(),
      "city": new FormControl(),
      "postcode": new FormControl(),
    });

    this.userDetailService.getUserById(Number(this.route.snapshot.paramMap.get('id'))).toPromise().then((UserDTO) => {
      this.userDTO = UserDTO;
    }).catch((error: HttpErrorResponse) => {
      this.errorHandling.openErrorBox(error.message);
    })

  }

  updateUser(): void {
    this.userDetailService.updateUser(this.userDTO).toPromise().then((UserDTO) => {
      console.log(UserDTO);
    }).catch((error: HttpErrorResponse) => {
      this.errorHandling.openErrorBox(error.message);
    })
  }
}
