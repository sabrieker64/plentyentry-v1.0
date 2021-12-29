import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-plentyentry-home',
  templateUrl: './plentyentry-home.component.html',
  styleUrls: ['./plentyentry-home.component.scss']
})
export class PlentyentryHomeComponent implements OnInit {

  constructor() {
  }

  ngOnInit(): void {

    console.log('this is our home default component');
  }

}
