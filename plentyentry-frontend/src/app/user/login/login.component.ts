import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-login-register',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor() {
  }

  ngOnInit(): void {
    console.log('this is the login component');
  }

}
