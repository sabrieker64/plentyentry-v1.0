import {Directive, ElementRef, HostListener, OnInit, Renderer2} from "@angular/core";

@Directive({
  selector: '[setActiveOnClick]'
})

export class SetActiveClassDirective implements OnInit {
  lastClickedNavItem: any;

  constructor(private elementRef: ElementRef, private renderer: Renderer2) {
  }

  ngOnInit() {

  }

  @HostListener('click') onClick() {
    console.log(this.elementRef)
    this.renderer.addClass(this.elementRef.nativeElement, 'active');

    if (this.lastClickedNavItem != undefined && this.lastClickedNavItem != this.elementRef.nativeElement) {
      this.renderer.removeClass(this.lastClickedNavItem, 'active');
    }

    this.lastClickedNavItem = this.elementRef.nativeElement;
  }

}
