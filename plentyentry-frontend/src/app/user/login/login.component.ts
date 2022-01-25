import {Component, Input, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-login-register',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  url: string = "http://httpbin.org/post";
  pass: string;
  mail: string;

  constructor(private router: Router, private http: HttpClient) {
    this.pass = "";
    this.mail = "";
  }

  postLogin() {
    this.http.post(this.url, {
      password: this.pass,
      email: this.mail
    }).toPromise().then((data: any) => {
      console.log(data);
    })
  }

  log(model:object) {console.log(model);}

  ngOnInit(): void {
    console.log('this is the login component');
  }

  openRegisterView() {
    return this.router.navigateByUrl('/user/register');
  }
}
