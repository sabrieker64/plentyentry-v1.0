import {Component, OnInit, Renderer2} from '@angular/core';
import {Router} from "@angular/router";
import {NavigationService} from "./navigation.service";
import {ErrorService} from "../../library/error-handling/error.service";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {
  navItems: { iconName: string, isActive: boolean, route: string }[];
  selectedNavItem: { iconName: string, isActive: boolean, route: string };

  constructor(private renderer: Renderer2, private router: Router, private navigationService: NavigationService, private errorHandling: ErrorService) {
    this.navItems = [
      {iconName: 'credit_score', isActive: false, route: '/ticket/bought-tickets'},
      {iconName: 'home', isActive: true, route: '/event/overview'},
      {iconName: 'login', isActive: false, route: '/user/login'}];
  }

  ngOnInit(): void {
  }

  onNavItemClick(selectedNavItem: {iconName: string, isActive:boolean, route: string}) {
    for (let navItem of this.navItems) {
      navItem.isActive = false;
    }
    selectedNavItem.isActive = true;
    console.log(selectedNavItem);
    if(selectedNavItem.iconName == 'home'){
      this.router.navigateByUrl('/event/overview');
    }
    if(selectedNavItem.iconName == 'login' && localStorage.getItem('token') != 'No token'){
      localStorage.setItem("token", "No token");
      window.location.reload();
    }
    if(selectedNavItem.iconName === 'credit_score'){
      if(localStorage.getItem('token') === 'No token' || localStorage.getItem('token') === null){
        this.errorHandling.openInformation('Sie müssen Sie anmelden um diese Funktion zu verwenden');
        this.router.navigateByUrl('/user/login');
      }else {
        this.router.navigateByUrl(selectedNavItem.route);
      }
    }
    if (selectedNavItem.iconName == 'manage_accounts') {
      this.navigationService.confirmToken().toPromise().then((data) => {
        console.log(data)
      })
    }
  }
}
