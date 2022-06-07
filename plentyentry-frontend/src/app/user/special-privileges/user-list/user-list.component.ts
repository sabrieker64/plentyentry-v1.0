import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {Router} from "@angular/router";
import {EventService} from "../../../events/service/event.service";
import {ErrorService} from "../../../../library/error-handling/error.service";
import {SpecialPrivilegesService} from "../service/special-privileges.service";
import {UserDTO} from "../../../definitions/objects";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {

  private loaded: boolean = false;
  staticPositions: number = 1;
  displayedColumns: string[] = ['position', 'firstName', 'email', 'city', 'age', 'birthday', 'userType', 'editUser', 'deleteUser'];
  allUsers: MatTableDataSource<UserDTO>;

  constructor(private specialPrivilegeService: SpecialPrivilegesService, private router: Router, private eventService: EventService, private errorHandling: ErrorService) {
  }

  ngOnInit(): void {
    this.loadAllUser();

    // Create a media condition that targets viewports at least 768px wide
    const mediaQuery = window.matchMedia('(max-width: 600px)')
    // Check if the media query is true
    if (mediaQuery.matches) {
      this.displayedColumns = ['email', 'userType', 'editUser', 'deleteUser'];
    }
  }


  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.allUsers.filter = filterValue.trim().toLowerCase();
  }

  loadAllUser() {
    return this.specialPrivilegeService.getAllUser().subscribe(users => {
      this.allUsers = new MatTableDataSource(users);

      if (users.length == 0) {
        this.errorHandling.openInformation("Keine User gefunden");
      }

      this.loaded = true;
      console.log(this.allUsers);
    }, error => {
      console.log(error);
    });

  }

  editUser(id: number) {
    this.router.navigateByUrl('/special-privileges/user/edit/' + id).then(r => {

    });
  }

  deleteUser(id: number) {
    if (confirm("Möchten Sie wirklich diesen Benutzer löschen?")) {
      this.specialPrivilegeService.deleteUserById(id).subscribe(users => {
        window.location.reload();
      }, error => {
        console.log(error);
      });

    }
  }

}
