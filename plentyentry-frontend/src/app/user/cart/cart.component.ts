import {Component, OnInit} from '@angular/core';
import {LoginRegisterService} from "../service/login-register.service";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {


  constructor(private service: LoginRegisterService) { }

  ngOnInit(): void {
    this.service.getFirstRestCall(2).pipe().subscribe(result => console.log(result));
  }

}
