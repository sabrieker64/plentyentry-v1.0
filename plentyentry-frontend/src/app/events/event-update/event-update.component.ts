import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {EventService} from "../service/event.service";
import {EventDTO} from "../../definitions/objects";
import {HttpErrorResponse} from "@angular/common/http";
import {ErrorService} from "../../../library/error-handling/error.service";

@Component({
  selector: 'app-event-update',
  templateUrl: './event-update.component.html',
  styleUrls: ['./event-update.component.scss']
})
export class EventUpdateComponent implements OnInit {
  eventId: number;
  eventDTO: EventDTO = <EventDTO>{};

  constructor(private activeRoute: ActivatedRoute, private eventService: EventService, private errorHandling: ErrorService) {
  }

  ngOnInit(): void {
    this.eventId = Number(this.activeRoute.snapshot.paramMap.get('id'));

    this.eventService.getEventById(this.eventId).toPromise().then((eventDTO) => {
      this.eventDTO = eventDTO;
    }).catch((error: HttpErrorResponse) => {
      this.errorHandling.openErrorBox(error.message);
    })
  }

  public updateEvent() {
    this.eventService.updateEventById(this.eventDTO).toPromise().then((eventDTO) => {
      console.log(eventDTO);
    }).catch((error: HttpErrorResponse) => {
      this.errorHandling.openErrorBox(error.message);
    })
  }
}
