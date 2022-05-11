import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MaintainerService} from "../service/maintainer.service";
import {EventDTO} from "../../../definitions/objects";

@Component({
  selector: 'app-maintained-events-list',
  templateUrl: './maintained-events-list.component.html',
  styleUrls: ['./maintained-events-list.component.scss']
})


export class MaintainedEventsListComponent implements OnInit {

  private loaded: boolean = false;

  constructor(private maintainerService: MaintainerService) {
  }

  ngOnInit(): void {
    this.loadAllMaintainedEvents();
  }

  staticPositions: number = 1;
  displayedColumns: string[] = ['position', 'name', 'date', 'description','price','ticketCounter','fullAddress', 'editMaintainedEvent'];
  allMaintainedEvents: MatTableDataSource<EventDTO>;

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.allMaintainedEvents.filter = filterValue.trim().toLowerCase();
  }

  loadAllMaintainedEvents() {
    return this.maintainerService.getMaintainedEvents().subscribe(maintainedEvents => {
      this.allMaintainedEvents = new MatTableDataSource(maintainedEvents);
      this.loaded = true;
    }, error => {
      console.log(error);
    });

  }

  editMaintainedEvent(id: number){
    console.log(id);
  }


}
