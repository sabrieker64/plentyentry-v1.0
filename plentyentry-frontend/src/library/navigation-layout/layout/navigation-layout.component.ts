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
    console.log('This is our Navigation Layout');
  }

  cartClicked(event: any) {
    return this.router.navigateByUrl('/user/1/cart');
  }

  accountClick(event: any) {
    return this.router.navigateByUrl('/user/login');
  }


}
