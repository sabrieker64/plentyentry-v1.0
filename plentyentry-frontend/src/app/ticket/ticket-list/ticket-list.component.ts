import {Component, OnInit} from '@angular/core';
import {TicketService} from "../service/ticket.service";
import {TicketDTO} from "../../definitions/objects";
import {DomSanitizer} from "@angular/platform-browser";
import {ErrorService} from "../../../library/error-handling/error.service";

@Component({
  selector: 'app-ticket-list',
  templateUrl: './ticket-list.component.html',
  styleUrls: ['./ticket-list.component.scss']
})
export class TicketListComponent implements OnInit {

  loaded: boolean = false;
  allTickets: TicketDTO[];
  qrCode: any = "";

  constructor(private ticketService: TicketService, private sanitizer: DomSanitizer, private errorHandling: ErrorService) {
  }

  ngOnInit(): void {
    this.loadAllEvents();
  }

  loadAllEvents() {
    return this.ticketService.getBoughtTickets().subscribe(tickets => {
      this.allTickets = tickets;
      this.loaded = true;

      if (tickets.length == 0) {
        this.errorHandling.openInformation("Sie haben noch keine Tickets gekauft");
      }

    }, error => {
      console.log(error);
    });
  }

  initializeQRCode(){

  }
  getEventQRCode(id: number, index: number) {

    this.ticketService.getQRCode(id).subscribe((base64: ArrayBuffer) => {

      var blob = new Blob([base64])
      var reader = new FileReader();
      reader.readAsText(blob);

      var base64data: string = "";

      reader.onloadend = (e: any) => {
        base64data = reader.result as string;
        this.setQrCode(base64data,index);
        console.log("erfolgreich");

      }
    }, error => {
      this.qrCode =  'data:image/jpg;base64,' + (this.sanitizer.bypassSecurityTrustResourceUrl(error.error.text));
      console.log(error);
      console.log("bin im Error");
    });

  }

  setQrCode(base64data: string, index: number) {
    this.qrCode = this.sanitizer.bypassSecurityTrustResourceUrl('data:image/png;base64,' + base64data.toString()) as string;
    this.qrCode = (this.qrCode.changingThisBreaksApplicationSecurity) as string;
    //CREATE IN MODEL QRCODE FIELD FOR ITERATING

    this.allTickets[index].qrCode = this.qrCode as string;
  }

  checkIfTicketIsValidToUse(ticket: TicketDTO): boolean {
    return ticket.ticketStatus === 'SELLED';
  }

  checkIfTicketIsUsed(ticket: TicketDTO): boolean {
    return ticket.ticketStatus === 'USED';
  }
}
