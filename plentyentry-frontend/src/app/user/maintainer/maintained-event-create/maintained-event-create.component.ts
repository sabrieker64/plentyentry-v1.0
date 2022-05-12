import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {EventDTO} from "../../../definitions/objects";
import {EventService} from "../../../events/service/event.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ErrorService} from "../../../../library/error-handling/error.service";

@Component({
  selector: 'app-maintained-event-create',
  templateUrl: './maintained-event-create.component.html',
  styleUrls: ['./maintained-event-create.component.scss']
})
export class MaintainedEventCreateComponent implements OnInit {


  createEventFormGroup: FormGroup;
  eventDTO: EventDTO = <EventDTO>{};

  constructor(private eventService: EventService,  private router: Router, private fb: FormBuilder, private errorHandling: ErrorService,  private route: ActivatedRoute) { }

  ngOnInit(): void {

    this.createEventFormGroup = this.fb.group({
      "address": new FormControl('', [Validators.required]),
      "city": new FormControl('', [Validators.required, Validators.minLength(2)]),
      "date": new FormControl(new Date()),
      "description": new FormControl('', [Validators.required, Validators.minLength(2)]),
      "name": new FormControl('', [Validators.required]),
      "price": new FormControl('', [Validators.required]),
      "ticketCounter": new FormControl('', [Validators.required]),
    });
  }

  createEvent() {
    //TODO -> EVENT NEEDS TICKET ID IN DB FOR CREATING
    this.eventService.createEvent(this.eventDTO).subscribe((data) => {
      this.router.navigateByUrl('/maintainedevents/maintained/events/list');
    }, error => {
      this.errorHandling.openErrorBox(error.message);
    });
  }

}
