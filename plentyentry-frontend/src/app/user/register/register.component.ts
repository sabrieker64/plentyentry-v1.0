import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

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
