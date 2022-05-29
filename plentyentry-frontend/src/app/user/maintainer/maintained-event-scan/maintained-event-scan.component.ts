import {Component, OnInit} from '@angular/core';
import {MaintainerService} from "../service/maintainer.service";


@Component({
  selector: 'app-maintained-event-scan',
  templateUrl: './maintained-event-scan.component.html',
  styleUrls: ['./maintained-event-scan.component.scss']
})
export class MaintainedEventScanComponent implements OnInit {


  constructor(private maintainerService: MaintainerService) {
  }

  qrResultString: string="";
  response: string="";

  clearResult() {
    this.qrResultString = "";
    this.response = "";
  }

  onCodeResult(resultString: string) {
    this.qrResultString = resultString;

    console.log(resultString);
    var ticketId = Number(resultString.split('scan/')[1]);

    console.log("-----------");
    console.log(ticketId);
    console.log("-----------");

    this.maintainerService.scanTicket(ticketId).subscribe((result: any) => {
      this.response = result.response;
      setTimeout(() => {
        this.response = "";
      }, 3000);
    }, error => {
      console.log(error);
    });


  }

  refreshScan() {
    this.response = "";
  }


  ngOnInit() {

  }

  ngAfterViewInit() {

  }


}
