import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FormControl, Validators} from "@angular/forms";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  vorname = '';
  url: string = "http://httpbin.org/post";
  fname: string;
  lname: string;
  place: string;
  adrss: string;
  zipcode: string;
  socialinsurancenumber: number;
  bday: number;
  pass: string;
  mail: string;
  textFormControl = new FormControl('', [Validators.minLength(2)]);
  email = '';
  password = '1';
  passwordMinLength = 8;
  emailFormControl = new FormControl('', [Validators.required, Validators.email]);
  passwordFormControl = new FormControl('', [Validators.required, Validators.minLength(this.passwordMinLength), Validators.pattern('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{0,}$')]);

  constructor(private http: HttpClient) {
    this.fname = "";
    this.lname = "";
    this.place = "";
    this.adrss = "";
    this.zipcode = "";
    this.socialinsurancenumber = 123456;
    this.bday = 123456;
    this.pass = "";
    this.mail = "";
  }

  postRegister() {
    this.http.post(this.url, {
      fname: this.fname,
      lname: this.lname,
      city: this.place,
      adress: this.adrss,
      zipcode: this.zipcode,
      socialinsurancenumber: this.socialinsurancenumber,
      bday: this.bday,
      pass: this.pass,
      mail: this.mail,
      password: this.pass,
      email: this.mail
    }).toPromise().then((data: any) => {
      console.log(data);
    })
  }

  ngOnInit(): void {
  }

}
