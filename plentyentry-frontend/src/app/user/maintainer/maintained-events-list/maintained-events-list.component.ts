import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MaintainerService} from "../service/maintainer.service";
import {EventDTO} from "../../../definitions/objects";
import {Router} from "@angular/router";
import {EventService} from "../../../events/service/event.service";
import {ErrorService} from "../../../../library/error-handling/error.service";
import {registerLocaleData} from "@angular/common";
import localeDe from "@angular/common/locales/de";
import localeDeExtra from "@angular/common/locales/extra/de";

@Component({
  selector: 'app-maintained-events-list',
  templateUrl: './maintained-events-list.component.html',
  styleUrls: ['./maintained-events-list.component.scss']
})


export class MaintainedEventsListComponent implements OnInit {

  private loaded: boolean = false;

  editEvent: {[key: number]: boolean} = {};

  constructor(private maintainerService: MaintainerService, private router: Router, private eventService: EventService, private errorHandling: ErrorService) {
  }

  ngOnInit(): void {
    this.loadAllMaintainedEvents();
    registerLocaleData(localeDe, 'de-DE', localeDeExtra);
  }

  staticPositions: number = 1;
  //displayedColumns: string[] = ['position', 'name', 'date', 'description', 'price', 'ticketCounter', 'fullAddress', 'editMaintainedEvent', 'deleteMaintainedEvent'];
  displayedColumns: string[] = ['name', 'date', 'description', 'price', 'ticketCounter', 'fullAddress', 'editMaintainedEvent', 'deleteMaintainedEvent'];
  allMaintainedEvents: MatTableDataSource<EventDTO>;

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.allMaintainedEvents.filter = filterValue.trim().toLowerCase();
  }

  loadAllMaintainedEvents() {
    return this.maintainerService.getMaintainedEvents().subscribe(maintainedEvents => {
      this.allMaintainedEvents = new MatTableDataSource(maintainedEvents);

      if (maintainedEvents.length == 0) {
        this.errorHandling.openInformation("Sie haben keine Events erstellt");
      }

      this.loaded = true;
      console.log(this.allMaintainedEvents);
    }, error => {
      console.log(error);
    });

  }

  editMaintainedEvent(id: number){
    this.router.navigateByUrl('/maintainedevents/maintained/events/edit/' + id);
  }

  deleteMaintainedEvent(id: number){

    /* TODO DELETE EVENT
    this.eventService.d
    //REFRESH PAGE
    this.router.routeReuseStrategy.shouldReuseRoute = function(){return false;};

    let currentUrl = this.router.url + '?';

    this.router.navigateByUrl(currentUrl)
      .then(() => {
        this.router.navigated = false;
        this.router.navigate([this.router.url]);
      });

     */
  }

}
