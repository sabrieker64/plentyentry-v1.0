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
   if (this.swUpdate.isEnabled) {
     this.swUpdate.checkForUpdate().then(data => {
       if(data == true){
         this.swUpdate.activateUpdate().then(() => {
           console.log('updating the app');
         });
       }
     });
    /*  this.swUpdate.versionUpdates.subscribe(() => {
          window.location.reload();
      });*/
    }
  }

  onActivate(event: any) {
    window.scroll(0,0);
  }
}
