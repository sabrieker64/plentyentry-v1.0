import {Injectable} from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class ErrorService {


  constructor(private errorBox: MatSnackBar, private router: Router) {
  }

  openErrorBox(message: string) {
    this.errorBox.open(message, 'Verstanden', {
      panelClass: ['error-box']
    });
  }

  openErrorBoxAndGoToLogin(message: string) {
    this.errorBox.open(message, 'Zum Login', {
      panelClass: ['warning-snackbar']
    }).onAction().subscribe(() => {
      this.router.navigateByUrl("/user/login");
    });
  }

  openInformation(message: string) {
    this.errorBox.open(message, 'Verstanden', {
      panelClass: ['warning-snackbar']
    });
  }

}
