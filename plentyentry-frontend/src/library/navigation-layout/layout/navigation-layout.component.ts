import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-navigation-layout',
  templateUrl: './navigation-layout.component.html',
  styleUrls: ['./navigation-layout.component.scss']
})
export class NavigationLayoutComponent implements OnInit {

  constructor() {
  }

  ngOnInit(): void {
  }

  cartClicked() {
    console.log('Cart Clicked')
  }

  accountClick() {
    console.log('Accoount Clicked')
  }
}
