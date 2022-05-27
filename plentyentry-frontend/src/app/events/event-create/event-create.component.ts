import {Component, OnInit} from '@angular/core';
import {EventDTO} from "../../definitions/objects";
import {EventService} from "../service/event.service";
import {HttpErrorResponse} from "@angular/common/http";
import {ErrorService} from "../../../library/error-handling/error.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {DomSanitizer} from "@angular/platform-browser";
import {MAT_DATE_FORMATS} from "@angular/material/core";

export const MY_DATE_FORMATS = {

  parse: {
    dateInput: 'DD/MM/YYYY',
  },

  display: {
    dateInput: 'DD/MM/YYYY',
    monthYearLabel: 'MMMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY'
  },

};


@Component({
  selector: 'app-event-create',
  templateUrl: './event-create.component.html',
  styleUrls: ['./event-create.component.scss'],
  providers: [
    {provide: MAT_DATE_FORMATS, useValue: 'MY_DATE_FORMATS'}
  ]
})
export class EventCreateComponent implements OnInit {

  startDateTimeDefault = new FormControl(new Date().getDate())
  endDateTimeDefault = new FormControl(new Date().getDate())
  eventImageCounter: number = 0;
  eventDTO: EventDTO = <EventDTO>{};
  createFormGroup: FormGroup;


  showEventImages: string[] = [];
  showEventImagesLoaded: boolean = false;

  accept: string = ".png, .jpg, .jpeg";

  constructor(private eventService: EventService, private errorHandling: ErrorService, private fb: FormBuilder, private router: Router, private sanitizer: DomSanitizer) {
  }

  ngOnInit(): void {
    this.createFormGroup = this.fb.group({
      "files": new FormControl([], [Validators.required]),
      "eventName": new FormControl('', [Validators.required, Validators.minLength(2)]),
      "eventCity": new FormControl('', [Validators.required, Validators.minLength(2)]),
      "eventStartDateTime": new FormControl(new Date().getDate()),
      "eventEndDateTime": new FormControl(new Date().getDate()),
      "eventAddress": new FormControl('', [Validators.required, Validators.minLength(2)]),
      "eventPrice": new FormControl('', [Validators.required]),
      "eventTicketCounter": new FormControl('', [Validators.required]),
      "eventDescription": new FormControl('', [Validators.required]),
    });


    this.eventDTO.startDateTime = new Date();
    this.eventDTO.endDateTime = new Date();


  }

  createEvent() {
    this.eventService.createEvent(this.eventDTO).toPromise().then((eventDTO: EventDTO) => {
      console.log(eventDTO);
      this.router.navigateByUrl("maintainedevents/maintained/events/list");
    }).catch((error: HttpErrorResponse) => {
      this.errorHandling.openErrorBox(error.message);
    })
  }


  onFileChange(event: any) {


    const files = <any>this.eventDTO.eventImageUrls;


    this.showEventImagesLoaded = false;
    this.showEventImages = [];

    if (files.length === 0)
      return;
    const mimeType = files[0].type;


    for (var i = 0; i < files.length; i++) {
      var currentFile = files[i];

      const reader = new FileReader();
      console.log("oi");
      reader.readAsDataURL(currentFile);
      reader.onload = (_event) => {
        var withoutBase = reader.result as string;
        withoutBase = withoutBase.split(',')[1];
        this.showEventImages.push(this.sanitizer.bypassSecurityTrustUrl('data:image/png;base64,' + withoutBase.toString()) as string);
      }

    }

    console.log("--------------")
    console.log(this.showEventImages);
    console.log("-------------")

    this.showEventImagesLoaded = true;


  }


}
