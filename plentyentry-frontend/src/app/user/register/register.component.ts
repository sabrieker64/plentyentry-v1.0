import {Component, OnInit} from '@angular/core';
import {FormControl} from "@angular/forms";
import {LoginRegisterService} from "../service/login-register.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  date = new FormControl(new Date());

  constructor(private loginRegisterService: LoginRegisterService) {

  }

  console(data:any) {

  }

  ngOnInit(): void {
  }

  register(gender:string, firstname:string, lastname:string, birthday:string, email:string, svNumber:string, adress:string, zipcode:string, city:string) {
    this.loginRegisterService.register(gender, firstname, lastname, birthday, email, svNumber, adress, zipcode, city);
  }

}
