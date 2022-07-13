import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {LoginRegisterService} from "../../../app/user/service/login-register.service";
import {UserDTO, UserType} from "../../../app/definitions/objects";
import {ErrorService} from "../../error-handling/error.service";
import {Router} from "@angular/router";
import {ShoppingcartService} from "../../../app/user/shoppingcart/service/shoppingcart.service";
import {UserDetailService} from "../../../app/user/service/user-detail.service";
import {Location} from '@angular/common';

@Component({
  selector: 'app-tool-bar',
  templateUrl: './tool-bar.component.html',
  styleUrls: ['./tool-bar.component.scss']
})
export class ToolBarComponent implements OnInit {

  loggedIn = false;
  shoppingCartValue: number = 0;
  userType: UserType;
  hasSpecialPriviliges: boolean = false;
  hasStdPriviliges: boolean = false;

  constructor(private loginRegisterService: LoginRegisterService, private http: HttpClient, private errorHandling: ErrorService, private router: Router,
              private shoppincartService: ShoppingcartService, private userService: UserDetailService, private location: Location) {
  }

  ngOnInit(): void {

    this.checkIfLoggedIn();
    this.loadShoppingCart();
  }

  ngOnChanges(): void {

  }

  loadShoppingCart() {
    return this.shoppincartService.getShoppingcart().subscribe(shoppingcart => {

      if (shoppingcart != null) {
        console.log("Shoppingcart vorhanden");

        var howManyOpen = 0;

        shoppingcart.tickets.filter(ticket => {
          ticket.ticketDTOS.forEach(tickets => {
            if (tickets.ticketStatus == 'RESERVED') {
              howManyOpen++;
            }
          })

        })

        this.shoppingCartValue = howManyOpen;
      } else {
        if (this.loggedIn == false) {

          this.errorHandling.openErrorBoxAndGoToLogin("Melden Sie sich an um alle Funktionen zu verwenden");

        }
      }
    }, () => {
      //console.log(error);
      //this.errorHandling.openErrorBox(error.message);
      this.errorHandling.openInformation('Bitte melden Sie sich an um alle Funktionen zu verwenden');
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

      if (loggedInUser.userType == "ADMIN" || loggedInUser.userType == "SUPERADMIN") {
        this.hasSpecialPriviliges = true;
        this.hasStdPriviliges = true;
      } else if (loggedInUser.userType == "MAINTAINER") {
        this.hasStdPriviliges = true;
      }

    }, (err) => {
      this.errorHandling.openErrorBoxAndGoToLogin("Loggen Sie sich bitte ein.")
    })

  }

  back(): void {
    this.location.back();
  }


  isNotHomeRoute() {
    return !this.router.url.toString().includes('/event/overview');
  }
}
