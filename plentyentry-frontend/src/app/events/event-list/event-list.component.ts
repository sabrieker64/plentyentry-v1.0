import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {EventDTO} from "../../definitions/objects";
import {EventService} from "../service/event.service";
import {HttpErrorResponse} from "@angular/common/http";
import {ErrorService} from "../../../library/error-handling/error.service";

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class EventListComponent implements OnInit {

  allEvents: EventDTO[];

  constructor(private eventService: EventService, private errorHandling: ErrorService) { }

  ngOnInit(): void {
    this.loadAllEvents();
  }

  loadAllEvents(){
    this.eventService.getBoughtTickets().toPromise().then((events) => {
      this.allEvents = events;
    }).catch((error: HttpErrorResponse) => {
      this.errorHandling.openErrorBox(error.message);
    });
  }

  getEventQRCode(id: number){

  }

  getMonth(date: Date): string {
    return new Date(date).toLocaleDateString('de-DE', {month: 'long'});
  }

  getDay(date: Date): string {
    return new Date(date).toLocaleDateString('de-DE', {day: 'numeric'});
  }


}
