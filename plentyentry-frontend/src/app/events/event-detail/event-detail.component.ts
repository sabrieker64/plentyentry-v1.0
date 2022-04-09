import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {EventDTO} from "../../definitions/objects";
import {ActivatedRoute} from "@angular/router";
import {EventService} from "../service/event.service";
import {HttpErrorResponse} from "@angular/common/http";
import {ErrorService} from "../../../library/error-handling/error.service";

@Component({
  selector: 'app-event-detail',
  templateUrl: './event-detail.component.html',
  styleUrls: ['./event-detail.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class EventDetailComponent implements OnInit {
  eventDTO: EventDTO = <EventDTO>{};
  eventQuantity: number = 0;

  constructor(private eventService: EventService, private route: ActivatedRoute, private errorHandling: ErrorService) {
  }

  ngOnInit(): void {
    this.getEventDetail();
  }

  public getEventDetail() {
    let eventId = Number(this.route.snapshot.paramMap.get('id'));

    if (!+isNaN(eventId)) {

    }
    this.eventService.getEventById(eventId).toPromise().then((event) => {
      this.eventDTO = event;
    }).catch((error: HttpErrorResponse) => {
      this.errorHandling.openErrorBox(error.message);
    })
  }

  public increaseQuantity() {
    this.eventQuantity++;
  }

  public decreaseQuantity() {
    if (this.eventQuantity > 0) {
      this.eventQuantity--;
    }
  }
}
