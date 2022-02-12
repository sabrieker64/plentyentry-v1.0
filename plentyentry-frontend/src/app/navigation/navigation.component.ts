import {Component, OnInit, Renderer2} from '@angular/core';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {
  lastClickedNavItem: any;

  constructor(private renderer: Renderer2) { }

  ngOnInit(): void {
  }

  onNavItemClick(event: Event) {
    this.renderer.addClass(event.currentTarget, 'active');
    if (this.lastClickedNavItem != undefined) {
      this.renderer.removeClass(this.lastClickedNavItem, 'active');
    }

    this.lastClickedNavItem = event.currentTarget;
  }
}
