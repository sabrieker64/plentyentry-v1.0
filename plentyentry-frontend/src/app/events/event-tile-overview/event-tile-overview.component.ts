import {Component, OnInit} from '@angular/core';
import {EventService} from "../service/event.service";
import {EventDTO} from "../../definitions/objects";
import {Router} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";
import {ErrorService} from "../../../library/error-handling/error.service";

@Component({
  selector: 'app-event-tile-overview',
  templateUrl: './event-tile-overview.component.html',
  styleUrls: ['./event-tile-overview.component.scss']
})
export class EventTileOverviewComponent implements OnInit {
  filteredEvents: EventDTO[];
  allEvents: EventDTO[];
  emptyList: boolean = false;
  dateFilter: boolean = false;

  constructor(private service: EventService, private router: Router, private errorHandling: ErrorService) {
  }

  ngOnInit(): void {
    this.loadEvents();
  }

  filterEventsWithSearch($event: any) {

    var searchedValue = "";
    if ($event.target != undefined) {
      searchedValue = $event.target.value;
    } else {
      searchedValue = $event.value;
    }


    if (searchedValue !== "") {

      this.filteredEvents = this.allEvents.filter(event => {

        var result = event.name.toLowerCase() + " " + event.city.toLowerCase();

        if (this.dateFilter == true) {
          var differenceInDays = this.calculateDays(event.startDateTime.toString());
          return result.includes(searchedValue.toLowerCase()) && (differenceInDays <= 14) && (differenceInDays >= 0);
        } else {
          return result.includes(searchedValue.toLowerCase());
        }
      });

    } else {
      if (this.dateFilter == true) {
        this.filteredEvents = this.allEvents.filter(event => {
          var differenceInDays = this.calculateDays(event.startDateTime.toString());
          return (differenceInDays <= 14 && differenceInDays >= 0);
        })
      } else {
        this.filteredEvents = this.allEvents;
      }
    }


    if (this.filteredEvents.length == 0) {
      this.emptyList = true;
    } else {
      this.emptyList = false;
    }

  }

  private calculateDays(startTime: string) {
    var startDateString = (startTime);
    startDateString = startDateString.split("T")[0];

    var startDate = new Date(startDateString);
    var today = new Date();

    var differenceInTime = startDate.getTime() - today.getTime();
    return (differenceInTime / (1000 * 3600 * 24));
  }

  private loadEvents() {
    this.service.getAllEvents().toPromise().then((events) => {
      this.allEvents = events;
      this.filteredEvents = events;
      if (this.allEvents.length == 0) {
        this.emptyList = true;
      }

    }).catch((error: HttpErrorResponse) => {
      this.errorHandling.openErrorBox(error.message);
    });
  }

  getMonth(date: Date): string {
    return new Date(date).toLocaleDateString('de-DE', {month: 'long'});
  }

  getDay(date: Date): string {
    return new Date(date).toLocaleDateString('de-DE', {day: 'numeric'});
  }

  getEventDetail(eventId: number) {
    this.router.navigateByUrl('/event-detail/' + eventId);
  }

  changeDateFilter($event: any) {
    if ($event.index == 1) {
      this.dateFilter = true;
    } else {
      this.dateFilter = false;
    }
    var currValue = document.getElementById("searchEventInputFilter");
    this.filterEventsWithSearch(currValue);
  }
}
