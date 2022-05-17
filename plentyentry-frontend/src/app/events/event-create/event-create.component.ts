import {Component, OnInit} from '@angular/core';
import {EventDTO} from "../../definitions/objects";
import {EventService} from "../service/event.service";
import {HttpErrorResponse} from "@angular/common/http";
import {ErrorService} from "../../../library/error-handling/error.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-event-create',
  templateUrl: './event-create.component.html',
  styleUrls: ['./event-create.component.scss']
})
export class EventCreateComponent implements OnInit {

  eventImageCounter: number = 0;
  eventDTO: EventDTO = <EventDTO>{};
  createFormGroup: FormGroup;

  accept: string = ".png, .jpg, .jpeg";

  constructor(private eventService: EventService, private errorHandling: ErrorService, private fb: FormBuilder, private router: Router) {
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


    const reader = new FileReader();
    if (event.target.files && event.target.files.length) {
      const [file] = event.target.files;
      reader.readAsDataURL(file);
      reader.onload = () => {
        this.eventDTO.eventImageUrls[this.eventImageCounter] = reader.result as string;

        this.eventImageCounter++;
      };
    }
  }


}
