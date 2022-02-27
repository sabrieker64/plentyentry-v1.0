import {Component, OnInit} from '@angular/core';
import {FormControl} from "@angular/forms";
import {LoginRegisterService} from "../service/login-register.service";
import {UserRegisterDTO} from "../../definitions/objects";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  date = new FormControl(new Date());
  userRegisterDTO: UserRegisterDTO = <UserRegisterDTO>{};

  constructor(private loginRegisterService: LoginRegisterService, private router: Router) {

  }

  console(data: any) {

  }

  ngOnInit(): void {
  }


  register() {
    this.loginRegisterService.registerNewUser(this.userRegisterDTO).toPromise().then(() => {
      this.router.navigateByUrl('/user/login');
    })

  }

}
