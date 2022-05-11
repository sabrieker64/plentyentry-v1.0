import {Component, OnInit, ViewChild} from '@angular/core';

@Component({
  selector: 'app-maintained-event-scan',
  templateUrl: './maintained-event-scan.component.html',
  styleUrls: ['./maintained-event-scan.component.scss']
})
export class MaintainedEventScanComponent implements OnInit {


  @ViewChild('videoElement') videoElement: any;
  video: any;

  constructor() { }

  ngOnInit() {

  }

  ngAfterViewInit(){
    console.log("OI")
    this.video = this.videoElement.nativeElement;
  }

  start() {
    this.initCamera({ video: true, audio: false });
  }
  sound() {
    this.initCamera({ video: true, audio: true });
  }

  initCamera(config:any) {
    var browser = <any>navigator;

    browser.getUserMedia = (browser.getUserMedia ||
      browser.webkitGetUserMedia ||
      browser.mozGetUserMedia ||
      browser.msGetUserMedia);

    browser.mediaDevices.getUserMedia(config).then(stream => {
      this.video.src = window.URL.createObjectURL(stream);
      this.video.play();
    });
  }

  pause() {
    this.video.pause();
  }

  toggleControls() {
    this.video.controls = this.displayControls;
    this.displayControls = !this.displayControls;
  }

  resume() {
    this.video.play();
  }

}
