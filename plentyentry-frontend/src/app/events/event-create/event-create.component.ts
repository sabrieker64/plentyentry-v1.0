import {Component, OnInit} from '@angular/core';
import {EventDTO} from "../../definitions/objects";
import {EventService} from "../service/event.service";
import {HttpErrorResponse} from "@angular/common/http";
import {ErrorService} from "../../../library/error-handling/error.service";

@Component({
  selector: 'app-event-create',
  templateUrl: './event-create.component.html',
  styleUrls: ['./event-create.component.scss']
})
export class EventCreateComponent implements OnInit {
  eventDTO: EventDTO = <EventDTO>{};

  constructor(private eventService: EventService, private errorHandling: ErrorService) {
  }

  ngOnInit(): void {

  }

  createEvent() {
    this.eventService.createEvent(this.eventDTO).toPromise().then((eventDTO: EventDTO) => {
      console.log(eventDTO);
    }).catch((error: HttpErrorResponse) => {
      this.errorHandling.openErrorBox(error.message);
    })
  }

}
