import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MaintainerService} from "../service/maintainer.service";
import {EventDTO} from "../../../definitions/objects";
import {Router} from "@angular/router";
import {EventService} from "../../../events/service/event.service";

@Component({
  selector: 'app-maintained-events-list',
  templateUrl: './maintained-events-list.component.html',
  styleUrls: ['./maintained-events-list.component.scss']
})


export class MaintainedEventsListComponent implements OnInit {

  private loaded: boolean = false;

  editEvent: {[key: number]: boolean} = {};

  constructor(private maintainerService: MaintainerService, private router: Router, private eventService: EventService) {
  }

  ngOnInit(): void {
    this.loadAllMaintainedEvents();
  }

  staticPositions: number = 1;
  displayedColumns: string[] = ['position', 'name', 'date', 'description', 'price', 'ticketCounter', 'fullAddress', 'editMaintainedEvent', 'deleteMaintainedEvent'];
  allMaintainedEvents: MatTableDataSource<EventDTO>;

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.allMaintainedEvents.filter = filterValue.trim().toLowerCase();
  }

  loadAllMaintainedEvents() {
    return this.maintainerService.getMaintainedEvents().subscribe(maintainedEvents => {
      this.allMaintainedEvents = new MatTableDataSource(maintainedEvents);
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
