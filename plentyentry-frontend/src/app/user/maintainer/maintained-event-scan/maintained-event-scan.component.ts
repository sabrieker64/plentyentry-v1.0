import {Component, OnInit, ViewChild} from '@angular/core';

@Component({
  selector: 'app-maintained-event-scan',
  templateUrl: './maintained-event-scan.component.html',
  styleUrls: ['./maintained-event-scan.component.scss']
})
export class MaintainedEventScanComponent implements OnInit {


  @ViewChild('videoElement') videoElement: any;
  video: any;

  backDeviceId: any;
  frontDeviceId: any;

  constructor() {
  }

  ngOnInit() {

  }

  ngAfterViewInit() {
    this.video = this.videoElement.nativeElement;


    if (navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {


      navigator.mediaDevices.enumerateDevices().then(res => {
          // @ts-ignore
          if (res.length > 0) {
            /* defaults so all this will work on a desktop */
            // @ts-ignore
            this.frontDeviceId = res[0].deviceId;
            // @ts-ignore
            this.backDeviceId = res[0].deviceId;
          }


          /* look for front and back devices */
          // @ts-ignore
          res.forEach(res => {
            if (res.kind === 'videoinput') {
              if (res.label && res.label.length > 0) {
                if (res.label.toLowerCase().indexOf('back') >= 0)
                  this.backDeviceId = res.deviceId
                else if (res.label.toLowerCase().indexOf('front') >= 0)
                  this.frontDeviceId = res.deviceId
              }
            }

            console.log(this.backDeviceId)

            navigator.mediaDevices.getUserMedia({
              audio: false,
              video: {

                facingMode: this.backDeviceId
              }
            })

            // @ts-ignore
            document.getElementById('streamVideo').addEventListener('click', async (e) => {
              const stream = await navigator.mediaDevices.getUserMedia({
                video: true
              })

              // @ts-ignore
              document.getElementById('video').srcObject = stream
            })
          })



        }
      )


    }


  }

  start() {


  }

  /*
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

   */

}
