import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {EventDTO} from "../../definitions/objects";
import {EventService} from "../service/event.service";

@Component({
  selector: 'app-event-detail',
  templateUrl: './event-detail.component.html',
  styleUrls: ['./event-detail.component.scss']
})
export class EventDetailComponent implements OnInit {
  eventId: number;
  eventDTO: EventDTO = <EventDTO>{};

  constructor(private activeRoute: ActivatedRoute, private eventService: EventService) {
  }

  ngOnInit(): void {
    this.eventId = Number(this.activeRoute.snapshot.paramMap.get('id'));

    this.eventService.getEventDetails(this.eventId).toPromise().then((eventDTO) => {
      this.eventDTO = eventDTO;
    })
  }

}
