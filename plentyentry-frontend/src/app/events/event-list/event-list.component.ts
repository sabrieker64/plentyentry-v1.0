import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {EventDTO} from "../../definitions/objects";
import {EventService} from "../service/event.service";
import {ErrorService} from "../../../library/error-handling/error.service";
import {Observable, of} from "rxjs";

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class EventListComponent implements OnInit {
  searchEmitted: boolean;
  expandedIndex = 0;
  allEvents: EventDTO[];

  constructor(private eventService: EventService, private errorHandling: ErrorService) {
  }

  ngOnInit(): void {
    console.log("test")
    this.loadAllEvents();
  }

  loadAllEvents() {
    return this.eventService.getBoughtTickets().subscribe(res => {
      console.log(res);
      this.allEvents = res;
    });
  }

  getEventQRCode(id: number) {

  }

  /*  getMonth(date: Date): string {
      return new Date(date).toLocaleDateString('de-DE', {month: 'long'});
    }

    getDay(date: Date): string {
      return new Date(date).toLocaleDateString('de-DE', {day: 'numeric'});
    }*/


  private complete(value: EventDTO[]) {
    this.allEvents = value;
    return value;
  }

  private handleError(): Observable<EventDTO> {
    return of();
  }
}
