import {Component, OnInit} from '@angular/core';
import {EventService} from "../service/event.service";
import {EventDTO} from "../../definitions/objects";
import {Router} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";
import {ErrorService} from "../../../library/error-handling/error.service";

@Component({
  selector: 'app-event-tile-overview',
  templateUrl: './event-tile-overview.component.html',
  styleUrls: ['./event-tile-overview.component.scss']
})
export class EventTileOverviewComponent implements OnInit {
  allEvents: EventDTO[];

  constructor(private service: EventService, private router: Router, private errorHandling: ErrorService) {
  }

  ngOnInit(): void {
    this.loadEvents();
  }

  private loadEvents() {
    this.service.getAllEvents().toPromise().then((events) => {
      this.allEvents = events;
    }).catch((error: HttpErrorResponse) => {
      this.errorHandling.openErrorBox(error.message);
    });
  }

  getMonth(date: Date): string {
    return new Date(date).toLocaleDateString('de-DE', {month: 'long'});
  }

  getDay(date: Date): string {
    return new Date(date).toLocaleDateString('de-DE', {day: 'numeric'});
  }

  getEventDetail(eventId: number) {
    this.router.navigateByUrl('/event-detail/' + eventId);
  }
}
