import {Component, OnInit} from '@angular/core';
import {SwUpdate} from "@angular/service-worker";
import {UpdateServiceService} from "./update-service/service/update-service.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'plentyentry-frontend';

  constructor(private updateService: UpdateServiceService) {
  }

  ngOnInit(): void {

    this.updateService.checkForUpdates()

  }
}
