import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";


@Component({
  selector: 'app-email-confirmation',
  templateUrl: './email-confirmation.component.html',
  styleUrls: ['./email-confirmation.component.scss']
})
export class EmailConfirmationComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<EmailConfirmationComponent>, @Inject(MAT_DIALOG_DATA) data) { }

  ngOnInit(): void {
  }


  closeDialog(){
    this.dialogRef.close(true);
  }

}
