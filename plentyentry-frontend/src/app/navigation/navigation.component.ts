import {Component, OnInit, Renderer2} from '@angular/core';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {
  navItems: {iconName: string, isActive:boolean, route: string}[];

  constructor(private renderer: Renderer2) {
    this.navItems = [
      {iconName: 'shopping_cart', isActive: false, route: '/user/1/cart'},
      {iconName: 'favorite', isActive:false, route: '/'},
      {iconName: 'home', isActive: true, route: '/event/overview'},
      {iconName: 'manage_accounts', isActive: false, route: '/user/login'}];
  }

  ngOnInit(): void {
  }

  onNavItemClick(selectedNavItem: {iconName: string, isActive:boolean, route: string}) {

    for (let navItem of this.navItems) {
      navItem.isActive = false;
    }

    selectedNavItem.isActive = true;
  }
}
