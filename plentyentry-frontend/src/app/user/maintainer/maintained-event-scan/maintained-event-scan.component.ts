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

  qrResultString: string = "";
  response: string = "";

  loading: boolean = false;

  clearResult() {
    this.qrResultString = "";
    this.response = "";
  }

  async onCodeResult(resultString: string) {
    await this.getResponse(resultString);
  }

  async getResponse(resultString: string) {

    if (this.loading == true) {
      return new Promise(resolve => {
        resolve("Loading..");
      });
    }

    this.loading = true;

    return new Promise(resolve => {
      this.qrResultString = resultString;

      console.log(resultString);
      var ticketId = Number(resultString.split('scan/')[1]);

      console.log("-----------");
      console.log(ticketId);
      console.log("-----------");

      this.maintainerService.scanTicket(ticketId).subscribe(async (result: any) => {
        this.response = result.response;
        setTimeout(async () => {
          await this.refreshResponse();
        }, 3000);
        resolve("done");
      }, error => {
        console.log(error);
      });
    });

  }


  async refreshResponse() {
    return new Promise(resolve => {
      this.loading = false;
      this.response = "";
      resolve('resolved');
    });
  }


  ngOnInit() {

  }

  ngAfterViewInit() {

  }


}
