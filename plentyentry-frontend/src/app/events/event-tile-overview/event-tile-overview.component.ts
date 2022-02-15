import {Component, EventEmitter, OnInit} from '@angular/core';
import {EventService} from "../service/event.service";
import {merge, Observable, of} from "rxjs";
import {EventDTO} from "../../definitions/objects";
import {catchError, filter, map, shareReplay, switchMap} from "rxjs/operators";
import {EventTileModel} from "./eventTile.model";

@Component({
  selector: 'app-event-tile-overview',
  templateUrl: './event-tile-overview.component.html',
  styleUrls: ['./event-tile-overview.component.scss']
})
export class EventTileOverviewComponent implements OnInit {
  dataObserver: Observable<EventDTO[]>;
  eventDTO: EventDTO[];
  searchPressed = new EventEmitter<boolean>();
  events: EventTileModel;
  currentFilter: Element;
  isTarget: boolean = false;
  filters: {name: string, isActive: boolean}[];

  constructor(private service: EventService) {
  }

  ngOnInit(): void {
    this.searchPressed.emit(true);
    this.service.getAllEvents().subscribe(data => this.finalize(data));
    let loadDataEvent = merge(this.searchPressed).pipe(switchMap(() => this.loadEvent()));
    this.dataObserver = loadDataEvent.pipe(
      map(data => this.finalize(data)),
      catchError(() => this.handleError()),
      shareReplay(1));
    this.filters = [
      {name: 'Top Events', isActive: false},
      {name: 'In meiner Nähe', isActive: false},
      {name: 'In Kürze', isActive: false},
    ]
  }

  console(event: Event) {
    console.log(typeof event.target);
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
    this.eventDTO = value;
    return value;
  }

  private handleError(): Observable<EventDTO[]> {
    return of();
  }

  onClickFilter(selectedFilter: {name: string, isActive:boolean}) {

    for (let filter of this.filters) {
      filter.isActive = false;
    }

    selectedFilter.isActive = true;
  }
}
