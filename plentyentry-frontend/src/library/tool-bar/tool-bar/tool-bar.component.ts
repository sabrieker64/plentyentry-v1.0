import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {LoginRegisterService} from "../../../app/user/service/login-register.service";
import {UserDTO} from "../../../app/definitions/objects";
import {ErrorService} from "../../error-handling/error.service";
import {Router} from "@angular/router";
import {LogoutComponent} from "../../../app/user/logout/logout.component";
import {ShoppingcartService} from "../../../app/user/shoppingcart/service/shoppingcart.service";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-tool-bar',
  templateUrl: './tool-bar.component.html',
  styleUrls: ['./tool-bar.component.scss']
})
export class ToolBarComponent implements OnInit {

  loggedIn=false;
  shoppingCartValue: number=0;

  constructor(private loginRegisterService: LoginRegisterService, private http: HttpClient, private errorHandling: ErrorService, private router: Router, private shoppincartService: ShoppingcartService) {
  }

  ngOnInit(): void {

    this.loggedIn=this.checkIfLoggedIn();
    this.loadShoppingCart();
  }

  ngOnChanges(): void {

  }

  loadShoppingCart() {
    return this.shoppincartService.getShoppingcart().subscribe(shoppingcart => {
      if(shoppingcart!=null){
        this.shoppingCartValue = shoppingcart.tickets.length;
      }
    }, error => {
      console.log(error);
    });

  }

  redirectUserDetail() {
    this.loginRegisterService.getUserByJWT().toPromise().then((userDTO: UserDTO) => {
      this.router.navigateByUrl('user/' + userDTO.id + '/detail');
    }).catch((error: HttpErrorResponse) => {
      this.errorHandling.openErrorBox(error.message);
    })
  }

  logout(){
    var token = localStorage.getItem('token');

    if(token!=null){
      localStorage.clear();
      this.router.navigateByUrl('/user/login');
      this.loggedIn=false;
    }
  }

  checkIfLoggedIn(){

    if(localStorage.getItem('token')){
      return true;
    }

    return false;

  }

}
