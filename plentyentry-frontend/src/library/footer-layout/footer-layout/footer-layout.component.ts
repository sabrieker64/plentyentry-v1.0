import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-footer-layout',
  templateUrl: './footer-layout.component.html',
  styleUrls: ['./footer-layout.component.scss']
})
export class FooterLayoutComponent implements OnInit {

  range: any;
  showFooter = false;
  ramMaxValue: number = 100;
  minValue = 0;
  maxValue = 100;
  step = 1;
  value = 1;


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

  showFooterChange() {
    this.showFooter = !this.showFooter;

  }


  getValues(value: any) {
    this.range = value;
    console.log(this.range);
  }

  onChange(value: any): void {
    this.range = value.valueOf();
  }

  setRam(event: any) {
    console.log(event);
  }

  modelChanged($event: any) {

  }

  getRange(value: string) {

  }
}
