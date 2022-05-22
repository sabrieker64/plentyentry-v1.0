import {Component, OnInit} from '@angular/core';
import {EventDTO} from "../../definitions/objects";
import {EventService} from "../service/event.service";
import {HttpErrorResponse} from "@angular/common/http";
import {ErrorService} from "../../../library/error-handling/error.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {delay} from "rxjs/operators";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app-event-create',
  templateUrl: './event-create.component.html',
  styleUrls: ['./event-create.component.scss']
})
export class EventCreateComponent implements OnInit {

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
      "eventDate": new FormControl(new Date()),
      "eventAddress": new FormControl('', [Validators.required, Validators.minLength(2)]),
      "eventPrice": new FormControl('', [Validators.required]),
      "eventTicketCounter": new FormControl('', [Validators.required]),
      "eventDescription": new FormControl('', [Validators.required]),
    });
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


    delay(1000);


    const files = <any>this.eventDTO.eventImageUrls;
    this.showEventImagesLoaded = false;
    this.showEventImages = [];

    if (files.length === 0)
      return;
    const mimeType = files[0].type;

    const reader = new FileReader();


    for (var i = 0; i < files.length; i++) {
      reader.readAsDataURL(files[i]);
      reader.onload = (_event) => {
        var withoutBase = reader.result as string;
        withoutBase = withoutBase.split(',')[1];
        this.showEventImages.push(this.sanitizer.bypassSecurityTrustUrl('data:image/png;base64,' + withoutBase.toString()) as string)
      }
    }


    this.showEventImagesLoaded = true;


  }


}
