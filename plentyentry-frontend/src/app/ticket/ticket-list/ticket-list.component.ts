import { Component, OnInit } from '@angular/core';
import {TicketService} from "../service/ticket.service";
import {TicketDTO} from "../../definitions/objects";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app-ticket-list',
  templateUrl: './ticket-list.component.html',
  styleUrls: ['./ticket-list.component.scss']
})
export class TicketListComponent implements OnInit {

  loaded: boolean = false;
  allTickets: TicketDTO[];
  qrCode: string = "";
  loadedQrCode: boolean = false;

  constructor(private ticketService: TicketService, private sanitizer: DomSanitizer) { }

  ngOnInit(): void {
    this.loadAllEvents();
  }

  loadAllEvents() {
    return this.ticketService.getBoughtTickets().subscribe(tickets => {
      this.allTickets = tickets;
      this.loaded=true;
    }, error => {
      console.log(error);
    });
  }

  initializeQRCode(){

  }
  getEventQRCode(id: number) {


    return this.ticketService.getQRCode(id).subscribe((base64: ArrayBuffer) => {

      var blob = new Blob([base64])
      var reader = new FileReader();
      reader.readAsText(blob);

      var finishedBase64Data;
      var base64data: any = "";
      reader.onloadend = (e: any) => {
        base64data = reader.result;
        finishedBase64Data = "data:image/jpeg;base64,"+ base64data;
        this.qrCode = 'data:image/jpg;base64,' + (this.sanitizer.bypassSecurityTrustResourceUrl(base64data.toString()));
        this.loadedQrCode = true;
      }

      console.log("erfolgreich");
    }, error => {
      this.qrCode =  'data:image/jpg;base64,' + (this.sanitizer.bypassSecurityTrustResourceUrl(error.error.text));
      console.log(error);
      console.log("bin im Error");
    });
  }

}
