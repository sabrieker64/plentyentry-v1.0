import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {EventDTO, UserRegisterDTO} from "../../../definitions/objects";
import {EventService} from "../../../events/service/event.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ErrorService} from "../../../../library/error-handling/error.service";
import {crossFieldValidator} from "../../../../library/custom-validators/crossField.validator";
import {HttpErrorResponse} from "@angular/common/http";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-maintained-event-edit',
  templateUrl: './maintained-event-edit.component.html',
  styleUrls: ['./maintained-event-edit.component.scss']
})
export class MaintainedEventEditComponent implements OnInit {

  loaded: boolean=false;

  updateEventFormGroup: FormGroup;
  eventDTO: EventDTO = <EventDTO>{};

  constructor(private eventService: EventService,  private router: Router, private fb: FormBuilder, private errorHandling: ErrorService,  private route: ActivatedRoute) { }

  ngOnInit(): void {

    this.getEvent();

    this.updateEventFormGroup = this.fb.group({
      "address": new FormControl('', [Validators.required]),
      "city": new FormControl('', [Validators.required, Validators.minLength(2)]),
      "date": new FormControl(new Date()),
      "description": new FormControl('', [Validators.required, Validators.minLength(2)]),
      "name": new FormControl('', [Validators.required]),
      "price": new FormControl('', [Validators.required]),
      "ticketCounter": new FormControl('', [Validators.required]),
    });
  }

  getEvent(){
    let id = this.route.snapshot.params.id;
    console.log(id);
    this.eventService.getEventById(id).subscribe((data)=>{
      this.eventDTO = data;
      this.loaded = true;
      console.log(this.eventDTO);
    }, error => {
      console.log(error);
    });
  }

  updateEvent() {
    this.eventService.updateEventById(this.eventDTO).subscribe((data) => {
      this.router.navigateByUrl('/maintainedevents/maintained/events/list');
    }, error => {
      this.errorHandling.openErrorBox(error.message);
    });
  }

}
