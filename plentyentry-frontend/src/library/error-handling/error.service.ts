import {Injectable} from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";

@Injectable({
  providedIn: 'root'
})
export class ErrorService {


  constructor(private errorBox: MatSnackBar) {
  }

  openErrorBox(message: string) {
    this.errorBox.open(message, 'Verstanden', {
      panelClass: ['error-box']
    })
  }
}
