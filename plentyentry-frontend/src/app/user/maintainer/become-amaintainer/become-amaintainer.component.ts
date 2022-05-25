import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {EventService} from "../../../events/service/event.service";
import {Router} from "@angular/router";
import {ErrorService} from "../../../../library/error-handling/error.service";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app-become-amaintainer',
  templateUrl: './become-amaintainer.component.html',
  styleUrls: ['./become-amaintainer.component.scss']
})
export class BecomeAMaintainerComponent implements OnInit {

  becomeAEntertainerFormGroup: FormGroup;

  firstName: string = "";
  lastName: string = "";
  companyName: string = "";
  uid: string = "";
  email: string = "";
  phoneNumber: string = "";
  description: string = "";


  //TODO: companyName AND uid AND PHONE NUMBER

  constructor(private eventService: EventService, private errorHandling: ErrorService, private fb: FormBuilder, private router: Router, private sanitizer: DomSanitizer) {
  }

  ngOnInit(): void {
    this.becomeAEntertainerFormGroup = this.fb.group({
      "firstName": new FormControl('', [Validators.required]),
      "lastName": new FormControl('', [Validators.required, Validators.minLength(2)]),
      "companyName": new FormControl('',),
      "uid": new FormControl('',),
      "email": new FormControl('', [Validators.required]),
      "phoneNumber": new FormControl('', [Validators.required]),
      "description": new FormControl('', [Validators.required]),
    });
  }

  becomeAEntertainerCall() {

  }


}
