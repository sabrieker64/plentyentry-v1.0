import {Component, OnInit, Renderer2} from '@angular/core';
import {Router} from "@angular/router";
import {NavigationService} from "./navigation.service";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {
  navItems: { iconName: string, isActive: boolean, route: string }[];
  selectedNavItem: { iconName: string, isActive: boolean, route: string };

  constructor(private renderer: Renderer2, private router: Router, private navigationService: NavigationService) {
    /*
    this.navItems = [
      {iconName: 'search', isActive: false, route: '/event/overview'},
      {iconName: 'home', isActive: true, route: '/event/overview'},
      {iconName: 'login', isActive: false, route: '/user/login'}];
      */

    let token = localStorage.getItem('token');
    if (token === null) {
      this.navItems = [
        {iconName: 'home', isActive: true, route: '/event/overview'}];
    } else {

      this.navItems = [
        {iconName: 'home', isActive: true, route: '/event/overview'},
        {iconName: 'login', isActive: false, route: '/user/login'}];
    }

  }

  ngOnInit(): void {
  }

  onNavItemClick(selectedNavItem: {iconName: string, isActive:boolean, route: string}) {
    for (let navItem of this.navItems) {
      navItem.isActive = false;
    }
    selectedNavItem.isActive = true;

    if (selectedNavItem.iconName == 'login') {
      localStorage.clear();
      window.location.reload();
    }

    if (selectedNavItem.iconName == 'manage_accounts') {
      this.navigationService.confirmToken().toPromise().then((data) => {
        console.log(data)
      })
    }
  }
}
