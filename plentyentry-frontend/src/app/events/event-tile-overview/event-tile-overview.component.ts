import {Component, OnInit} from '@angular/core';
import {EventTile} from "./eventTile";
import {Observable} from "rxjs";
import {EventDTO} from "../../definitions/objects";

@Component({
  selector: 'app-event-tile-overview',
  templateUrl: './event-tile-overview.component.html',
  styleUrls: ['./event-tile-overview.component.scss']
})
export class EventTileOverviewComponent implements OnInit {

  private _events: EventTile[];

  private realEvents: Observable<EventDTO[]>;

  constructor() {
    this._events = this.getData();
    this.realEvents = new Observable<EventDTO[]>();
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


    eventEllmau = new EventTile('Ellmau','27.12.2022', 'https://images.eventpeppers.com/sites/default/files/imagecache/article-full/content/18-05/disco-feiern-abends.jpg', 'Wirschtlparty Schicker', 10,2,1);
    eventSJO = new EventTile('Sankt Johann in Tirol','28.12.2022','https://cdn.prod.www.spiegel.de/images/f3cb97f7-02b4-4ae4-8089-a0f614499499_w1280_r1.77_fpx46.65_fpy50.jpg', 'Bombenparty Gallab',15,2,2);
    eventKitz = new EventTile('Kitzbühel','29.12.2022', 'https://backend.instyle.de/sites/instyle.de/files/images/2017-08/party-looks-outfits.jpg','Feuerwehrfest',30,2,1);
    eventFieberbrunn = new EventTile('Fieberbrunn','30.12.2022', 'https://m.faz.net/media1/ppmedia/aktuell/3384622979/1.7543413/mmobject-still_full/menschen-feiern-in-einem-club.jpg','Bourbon Street',5,2,1);

    return [eventEllmau, eventSJO, eventKitz, eventFieberbrunn];
  }


  get events(): EventTile[] {
    return this._events;
  }


}
