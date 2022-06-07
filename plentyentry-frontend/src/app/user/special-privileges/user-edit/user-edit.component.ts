import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {UserDTO, UserType} from "../../../definitions/objects";
import {LoginRegisterService} from "../../service/login-register.service";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {ErrorService} from "../../../../library/error-handling/error.service";
import {HttpErrorResponse} from "@angular/common/http";
import {SpecialPrivilegesService} from "../service/special-privileges.service";

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.scss']
})
export class UserEditComponent implements OnInit {

  editFormGroup: FormGroup;


  userDTO: UserDTO = <UserDTO>{};
  paramUserId: number;
  allowed: boolean = false;


  userTypes: UserType[] = ["SUPERADMIN", "ADMIN", "MAINTAINER", "CUSTOMER"];

  currentUserId: number = 0;

  constructor(private specialPrivilegesService: SpecialPrivilegesService, private fb: FormBuilder,
              private loginRegisterService: LoginRegisterService, private route: ActivatedRoute,
              private errorHandling: ErrorService, private router: Router,) {
  }

  async ngOnInit(): Promise<any> {

    this.route.paramMap.subscribe((res: ParamMap) => {
      this.currentUserId = res.get('id') as unknown as number;
      console.log(this.currentUserId);

      this.editFormGroup = this.fb.group({
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
        "userType": new FormControl(),
        "companyName": new FormControl(),
        "phoneNumber": new FormControl(),
      });

      this.loadUserDetails(this.currentUserId).then((res) => {

      }, (err) => {
        this.errorHandling.openInformation("Melden Sie sich bitte an!");
      });


      console.log(this.userTypes);
    });

  }


  async loadUserDetails(id: number) {
    let token = localStorage.getItem('token');

    if (token != null) {
      this.specialPrivilegesService.getUserById(id).toPromise().then((UserDTO) => {
        this.userDTO = UserDTO;
        this.allowed = true;
      }).catch((error: HttpErrorResponse) => {
        this.errorHandling.openErrorBox(error.message);
      })
    }
  }

  updateUser(): void {
    this.specialPrivilegesService.updateUser(this.userDTO).toPromise().then((UserDTO) => {
      console.log(UserDTO);
      this.router.navigateByUrl("/special-privileges/user/list");
    }).catch((error: HttpErrorResponse) => {
      this.errorHandling.openErrorBox(error.message);
    })
  }

}
