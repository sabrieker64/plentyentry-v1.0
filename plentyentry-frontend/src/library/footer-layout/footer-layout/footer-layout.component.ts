import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-footer-layout',
  templateUrl: './footer-layout.component.html',
  styleUrls: ['./footer-layout.component.scss']
})
export class FooterLayoutComponent implements OnInit {

  showFooter = false;


  constructor() {
  }

  ngOnInit(): void {
  }

  formatLabel(value: number) {
    if (value >= 1000) {
      return Math.round(value / 1000) + 'k';
    }

    return value;
  }

  showFooterChange(){
    this.showFooter = !this.showFooter;

  }






}
