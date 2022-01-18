import {Component, OnInit} from '@angular/core';
import {EventTile} from "./eventTile";

@Component({
  selector: 'app-event-tile-overview',
  templateUrl: './event-tile-overview.component.html',
  styleUrls: ['./event-tile-overview.component.scss']
})
export class EventTileOverviewComponent implements OnInit {

  private _events: EventTile[];

  constructor() {
    this._events = this.getData();
  }

  ngOnInit(): void {
    console.log('This is our tile overview');
  }

  buyClicked() {
    console.log('buying clicked');
  }

  inToCartClicked() {
    console.log('In to Cart clicked');
  }

  getData() {
    var eventEllmau: EventTile;
    var eventSJO: EventTile;
    var eventKitz: EventTile;
    var eventFieberbrunn: EventTile;


    eventEllmau = new EventTile('Ellmau','27.12.2022', 'Schickis Houseparty',3,1);
    eventSJO = new EventTile('Sankt Johann in Tirol','28.12.2022', 'Jogassn',1,2);
    eventKitz = new EventTile('Kitzbühel','29.12.2022', 'Feuerwehrfest',1,1);
    eventFieberbrunn = new EventTile('Fieberbrunn','30.12.2022', 'Bourbon Street',2,1);

    return [eventEllmau, eventSJO, eventKitz, eventFieberbrunn];
  }


  get events(): EventTile[] {
    return this._events;
  }
}
