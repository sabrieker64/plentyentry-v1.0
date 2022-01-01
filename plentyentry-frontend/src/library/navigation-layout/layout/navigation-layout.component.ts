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
  }

  accountClick() {
    console.log('Accoount Clicked');
    this.router.navigateByUrl('/user/login');
  }
}
