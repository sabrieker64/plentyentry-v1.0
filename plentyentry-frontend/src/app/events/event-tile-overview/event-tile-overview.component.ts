import {Component, EventEmitter, OnInit} from '@angular/core';
import {EventService} from "../service/event.service";
import {EventDTO} from "../../definitions/objects";
import {EventTileModel} from "./eventTile.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-event-tile-overview',
  templateUrl: './event-tile-overview.component.html',
  styleUrls: ['./event-tile-overview.component.scss']
})
export class EventTileOverviewComponent implements OnInit {
  eventDTO: EventDTO[] = <EventDTO[]>{};
  searchPressed = new EventEmitter<boolean>();
  events: EventTileModel;
  currentFilter: Element;
  isTarget: boolean = false;
  filters: {name: string, isActive: boolean}[];

  constructor(private eventService: EventService, private router: Router) {
  }

  ngOnInit(): void {
    this.searchPressed.emit(true);
    this.filters = [
      {name: 'Top Events', isActive: false},
      {name: 'In meiner Nähe', isActive: false},
      {name: 'In Kürze', isActive: false},
    ]

    this.eventService.getAllEvents().toPromise().then((eventDTO) => {
      this.eventDTO = eventDTO;
    });
  }

  getEventDetail(eventId: number) {
    this.router.navigateByUrl('overview/detail/' + eventId);
    console.log(eventId);
  }

  getMonth(date: Date): string {
    return new Date(date).toLocaleDateString('de-DE', {month: 'long'});
  }

  getDay(date: Date) {
    return new Date(date).toLocaleDateString('de-DE', {day: 'numeric'});
  }

  buyClicked() {
    console.log('buying clicked');
  }

  inToCartClicked() {
    console.log('In to Cart clicked');
  }

  onClickFilter(selectedFilter: {name: string, isActive:boolean}) {

    for (let filter of this.filters) {
      filter.isActive = false;
    }

    selectedFilter.isActive = true;
  }
}
