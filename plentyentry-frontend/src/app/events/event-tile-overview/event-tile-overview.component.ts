import {Component, OnInit} from '@angular/core';
import {EventService} from "../service/event.service";
import {merge, Observable} from "rxjs";
import {EventDTO} from "../../definitions/objects";
import {map, switchMap} from "rxjs/operators";

@Component({
  selector: 'app-event-tile-overview',
  templateUrl: './event-tile-overview.component.html',
  styleUrls: ['./event-tile-overview.component.scss']
})
export class EventTileOverviewComponent implements OnInit {
  data: Observable<EventDTO[]>;
  searchPressed: any;

  constructor(private service: EventService) {
  }

  ngOnInit(): void {
    const loadDataEvent = merge(this.searchPressed).pipe(switchMap(() => this.loadEvent()));
    this.data = loadDataEvent.pipe(map(data => this.finalize(data)))

  }

  private loadEvent(): Observable<EventDTO[]> {
    return this.service.getAllEvents();
  }

  buyClicked() {
    console.log('buying clicked');
  }

  inToCartClicked() {
    console.log('In to Cart clicked');
  }

  private finalize(value: EventDTO[]): EventDTO[] {
    return value;
  }
}
