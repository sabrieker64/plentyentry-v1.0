import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-navigation-layout',
  templateUrl: './navigation-layout.component.html',
  styleUrls: ['./navigation-layout.component.scss']
})
export class NavigationLayoutComponent implements OnInit {

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  cartClicked() {
    console.log('Cart Clicked')
    //TODO: Make a User service and get the logged in user route by User id
    return this.router.navigateByUrl('/user/' +1+'/cart')
  }

  accountClick() {
    console.log('Accoount Clicked');
    return this.router.navigateByUrl('/user/login');
  }
}
