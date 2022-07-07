import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {EventDTO} from "../../../definitions/objects";
import {EventService} from "../../../events/service/event.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ErrorService} from "../../../../library/error-handling/error.service";
import {registerLocaleData} from "@angular/common";
import localeDe from "@angular/common/locales/de";
import localeDeExtra from "@angular/common/locales/extra/de";
import {DomSanitizer} from "@angular/platform-browser";

/*
const CUSTOM_DATE_FORMATS: NgxMatDateFormats = {
  parse: {
    dateInput: "l, LTS"
  },
  display: {
    dateInput: "l, LTS",
    monthYearLabel: "MMM YYYY",
    dateA11yLabel: "LL",
    monthYearA11yLabel: "MMMM YYYY"
  }
};

 */


@Component({
  selector: 'app-maintained-event-edit',
  templateUrl: './maintained-event-edit.component.html',
  styleUrls: ['./maintained-event-edit.component.scss'],
  /*{

    providers: [
     //{provide: MAT_DATE_LOCALE, useValue: MY_FORMATS}


       provide: NgxMatDateAdapter,
       useClass: MaintainedEventEditComponent,
       deps: [MAT_DATE_LOCALE, CUSTOM_DATE_FORMATS]
     }

    ]*/
})

export class MaintainedEventEditComponent implements OnInit {

  loaded: boolean = false;

  startDateTimeDefault = new FormControl(new Date().getDate())
  endDateTimeDefault = new FormControl(new Date().getDate())
  updateEventFormGroup: FormGroup;
  eventDTO: EventDTO = <EventDTO>{};
  accept: string = ".png, .jpg, .jpeg";
  showEventImages: string[] = [];
  eventImagesBase64List: string[] = [];
  currentEventImageUrls: string[] = [];

  constructor(private eventService: EventService, private router: Router, private fb: FormBuilder, private errorHandling: ErrorService, private route: ActivatedRoute, private sanitizer: DomSanitizer) {
  }

  ngOnInit(): void {

    this.getEvent();

    this.updateEventFormGroup = this.fb.group({
      "files": new FormControl([]),
      "address": new FormControl('', [Validators.required]),
      "city": new FormControl('', [Validators.required, Validators.minLength(2)]),
      "startDateTime": new FormControl(new Date()),
      "endDateTime": new FormControl(new Date()),
      "description": new FormControl('', [Validators.required, Validators.minLength(2)]),
      "name": new FormControl('', [Validators.required]),
      "price": new FormControl('', [Validators.required]),
      "ticketCounter": new FormControl('', [Validators.required]),
    });
    registerLocaleData(localeDe, 'de-DE', localeDeExtra);
  }

  getEvent(){
    let id = this.route.snapshot.params.id;
    console.log(id);
    this.eventService.getEventById(id).subscribe((data) => {
      this.eventDTO = data;

      this.eventDTO.eventImageUrls.forEach((url, index) => {
        this.showEventImages.push(url)
      })


      this.loaded = true;

    }, error => {
      console.log(error);
    });
  }

  async getBase64ImageFromUrl(imageUrl: string) {
    var res = await fetch(imageUrl);
    var blob = await res.blob();

    return new Promise((resolve, reject) => {
      var reader = new FileReader();
      reader.addEventListener("load", function () {
        resolve(reader.result);
      }, false);

      reader.onerror = () => {
        return reject(this);
      };
      reader.readAsDataURL(blob);
    })
  }

  setCurrentShowEventImages() {


    /*
    setTimeout(() => {
      this.eventDTO.eventImageUrls.forEach((url, index) => {
        this.getBase64ImageFromUrl(url)
          .then((result: any) => {
            console.log(result)
            this.showEventImages.push(result)
          })
          .catch(err => console.error(err));
      })
    }, 300)

     */

  }


  updateEvent() {

    console.log(this.updateEventFormGroup.value);
    if (this.eventDTO.startDateTime > this.eventDTO.endDateTime) {

      this.errorHandling.openInformation("Bitte ändern Sie das Enddatum!");

      return;
    }

    this.eventDTO.eventImageUrls = [];

    this.eventDTO.eventImageUrls = this.eventImagesBase64List;

    this.eventDTO.startDateTime = new Date(this.eventDTO.startDateTime);
    this.eventDTO.startDateTime.setHours(this.eventDTO.startDateTime.getHours() + 2);

    this.eventDTO.endDateTime = new Date(this.eventDTO.endDateTime);
    this.eventDTO.endDateTime.setHours(this.eventDTO.endDateTime.getHours() + 2);

    this.eventService.updateEventById(this.eventDTO).subscribe((data) => {
      this.router.navigateByUrl('/maintainedevents/maintained/events/list');
    }, error => {
      this.errorHandling.openErrorBox(error.message);
    });
  }

  deleteImage(i: number) {
    this.eventDTO.eventImageUrls.splice(i, i)
    console.log(this.eventDTO.eventImageUrls)
  }

  onFileChange(event: any) {
    const files = this.updateEventFormGroup.controls['files'].value;
    if (files.length === 0){
      return;
    }

    this.showEventImages = [];

    for (var i = 0; i < files.length; i++) {
      var currentFile = files[i];
      const mimeType = files[i].type;

      const reader = new FileReader();
      console.log(currentFile);
        reader.readAsDataURL(currentFile);
        reader.onload = (_event) => {
          let withoutBase = reader.result as string;
          withoutBase = withoutBase.split(',')[1];
          this.showEventImages.push(this.sanitizer.bypassSecurityTrustUrl('data:' + mimeType + ';base64,' + withoutBase.toString()) as string);
          this.eventImagesBase64List.push('data:' + mimeType + ';base64,' + withoutBase.toString());
      }
    }
  }

}
