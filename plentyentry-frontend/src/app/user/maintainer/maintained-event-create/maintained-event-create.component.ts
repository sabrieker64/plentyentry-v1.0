import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {EventDTO} from "../../../definitions/objects";
import {EventService} from "../../../events/service/event.service";
import {Router} from "@angular/router";
import {ErrorService} from "../../../../library/error-handling/error.service";
import {DomSanitizer} from "@angular/platform-browser";
import {HttpErrorResponse} from "@angular/common/http";
import {MAT_DATE_FORMATS} from "@angular/material/core";

@Component({
  selector: 'app-maintained-event-create',
  templateUrl: './maintained-event-create.component.html',
  styleUrls: ['./maintained-event-create.component.scss'],
  providers: [
    {provide: MAT_DATE_FORMATS, useValue: 'MY_DATE_FORMATS'}
  ]
})
export class MaintainedEventCreateComponent implements OnInit {


  startDateTimeDefault = new FormControl(new Date().getDate())
  endDateTimeDefault = new FormControl(new Date().getDate())
  eventImageCounter: number = 0;
  eventDTO: EventDTO = <EventDTO>{};
  eventImagesBase64List: string[] = [];
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
      "eventStartDateTime": new FormControl(new Date().getDate(), [Validators.required]),
      "eventEndDateTime": new FormControl(new Date().getDate(), [Validators.required]),
      "eventAddress": new FormControl('', [Validators.required, Validators.minLength(2)]),
      "eventPrice": new FormControl('', [Validators.required]),
      "eventTicketCounter": new FormControl('', [Validators.required]),
      "eventDescription": new FormControl('', [Validators.required]),
    });


    this.eventDTO.startDateTime = new Date();
    this.eventDTO.endDateTime = new Date();


  }

  createEvent() {


    if (this.eventDTO.startDateTime > this.eventDTO.endDateTime) {

      this.errorHandling.openInformation("Bitte ändern Sie das Enddatum!");

      return;
    }


    this.eventDTO.eventImageUrls = [];
    this.eventDTO.eventImageUrls = this.eventImagesBase64List;

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


    for (var i = 0; i < files.length; i++) {
      var currentFile = files[i];
      const mimeType = files[i].type;

      const reader = new FileReader();
      reader.readAsDataURL(currentFile);
      reader.onload = (_event) => {
        var withoutBase = reader.result as string;
        withoutBase = withoutBase.split(',')[1];
        this.showEventImages.push(this.sanitizer.bypassSecurityTrustUrl('data:' + mimeType + ';base64,' + withoutBase.toString()) as string);
        this.eventImagesBase64List.push('data:' + mimeType + ';base64,' + withoutBase.toString());
      }

    }


    this.showEventImagesLoaded = true;


  }

}

