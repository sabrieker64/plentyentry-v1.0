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

  cartClicked(event: any) {
    if(event){
      this.router.navigateByUrl('/user/1/cart');
    }

  }

  accountClick(event: any) {
    if(event){
      this.router.navigateByUrl('/user/login');
    }
  }
}
