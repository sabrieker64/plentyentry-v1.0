import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {LoginRegisterService} from "../../../app/user/service/login-register.service";
import {UserDTO, UserType} from "../../../app/definitions/objects";
import {ErrorService} from "../../error-handling/error.service";
import {Router} from "@angular/router";
import {ShoppingcartService} from "../../../app/user/shoppingcart/service/shoppingcart.service";
import {UserDetailService} from "../../../app/user/service/user-detail.service";

@Component({
  selector: 'app-tool-bar',
  templateUrl: './tool-bar.component.html',
  styleUrls: ['./tool-bar.component.scss']
})
export class ToolBarComponent implements OnInit {

  loggedIn = false;
  shoppingCartValue: number = 0;
  userType: UserType;
  hasSuperPriviliges: boolean = false;

  constructor(private loginRegisterService: LoginRegisterService, private http: HttpClient, private errorHandling: ErrorService, private router: Router, private shoppincartService: ShoppingcartService, private userService: UserDetailService) {
  }

  ngOnInit(): void {

    this.checkIfLoggedIn();
    this.loadShoppingCart();
  }

  ngOnChanges(): void {

  }

  loadShoppingCart() {
    return this.shoppincartService.getShoppingcart().subscribe(shoppingcart => {
      console.log(shoppingcart);
      if (shoppingcart != null) {
        this.shoppingCartValue = shoppingcart.tickets.length;
      } else {
        this.errorHandling.openErrorBoxAndGoToLogin("Bitte melden Sie sich an, um alle Features verwenden zu können");
      }
    }, error => {
      //console.log(error);
      //this.errorHandling.openErrorBox(error.message);
      this.errorHandling.openErrorBoxAndGoToLogin("Bitte melden Sie sich an, um alle Features verwenden zu können");

    });

  }

  redirectUserDetail() {
    this.loginRegisterService.getUserByJWT().toPromise().then((userDTO: UserDTO) => {
      this.router.navigateByUrl('user/detail');
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

  checkIfLoggedIn() {


    this.userService.getUserByJWT().subscribe((loggedInUser) => {
      if (localStorage.getItem('token')) {
        this.loggedIn = true;
      } else {
        this.loggedIn = false;
      }

      if (loggedInUser.userType == "ADMIN" || loggedInUser.userType == "SUPERADMIN" || loggedInUser.userType == "MAINTAINER") {
        this.hasSuperPriviliges = true;
      }

    }, (err) => {
      this.errorHandling.openErrorBoxAndGoToLogin("Loggen Sie sich bitte ein.")
    })


  }

}
