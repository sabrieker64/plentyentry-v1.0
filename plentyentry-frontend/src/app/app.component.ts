import {Component, OnInit} from '@angular/core';
import {SwUpdate} from "@angular/service-worker";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'plentyentry-frontend';

  constructor(private swUpdate: SwUpdate) {
  }

  ngOnInit(): void {
  /*  if (this.swUpdate.isEnabled) {
      this.swUpdate.available.subscribe(() => {
        if (confirm("Neue Version von PlentyEntry ist verfügbar, soll die neue Version geladen werden?")) {
          window.location.reload();
        }
      });
    }*/
  }
}
