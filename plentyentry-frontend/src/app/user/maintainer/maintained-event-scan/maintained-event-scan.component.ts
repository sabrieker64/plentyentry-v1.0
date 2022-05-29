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

  successBackground: boolean = true;

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

      this.maintainerService.scanTicket(ticketId).subscribe(async (result: any) => {

        if (result.response != null && result.response != "" && !result.response.empty) {
          this.successBackground = true;
          this.response = result.response;
        } else {
          this.successBackground = false;
          this.response = result.responseerror;
        }


        setTimeout(async () => {
          await this.refreshResponse();
        }, 5000);
        resolve("done");
      }, error => {
        this.response = "Serverprobleme";
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
