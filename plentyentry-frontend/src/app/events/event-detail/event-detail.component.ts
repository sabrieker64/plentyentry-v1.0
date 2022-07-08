import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {EventDTO, TicketDTO} from "../../definitions/objects";
import {ActivatedRoute, Router} from "@angular/router";
import {EventService} from "../service/event.service";
import {HttpErrorResponse} from "@angular/common/http";
import {ErrorService} from "../../../library/error-handling/error.service";
import {TicketService} from "../../ticket/service/ticket.service";
import {UserDetailService} from "../../user/service/user-detail.service";
import localeDe from '@angular/common/locales/de';
import localeDeExtra from '@angular/common/locales/extra/de';
import {registerLocaleData} from "@angular/common";

@Component({
  selector: 'app-event-detail',
  templateUrl: './event-detail.component.html',
  styleUrls: ['./event-detail.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class EventDetailComponent implements OnInit {
  eventDTO: EventDTO = <EventDTO>{};
  eventQuantity: number = 1;
  selectedTickets: TicketDTO[];


  constructor(private eventService: EventService, private route: ActivatedRoute,
              private errorHandling: ErrorService, private router: Router,
              private ticketService: TicketService, private userService: UserDetailService) {
  }


  ngOnInit(): void {
    this.getEventDetail();
    registerLocaleData(localeDe, 'de-DE', localeDeExtra);
  }

  public getEventDetail() {
    let eventId = Number(this.route.snapshot.paramMap.get('id'));

    if (!+isNaN(eventId)) {

    }
    this.eventService.getEventById(eventId).toPromise().then((event) => {
      this.eventDTO = event;
    }).catch((error: HttpErrorResponse) => {
      this.errorHandling.openErrorBox(error.message);
    })
  }

  public increaseQuantity() {
    this.eventQuantity++;
  }

  public decreaseQuantity() {
    if (this.eventQuantity > 1) {
      this.eventQuantity--;
    }
  }

  addToShoppingCart(eventDTO: EventDTO, quantity: number) {
    if(localStorage.getItem("token") == null || localStorage.getItem("token") == 'No token'){
      this.router.navigateByUrl('/user/login');
      localStorage.setItem('eventId', eventDTO.id.toString());
      localStorage.setItem('quantity', quantity.toString());
    }
    this.eventService.selectTicketsAndAddToCustomerShoppingCart(eventDTO.id, quantity).toPromise().then(data => {
      console.log('successfully added to your shopping cart'+  data);
      this.router.navigateByUrl('/shoppingcart/list');
    });
  }

  private ticketFinalize(data: TicketDTO[]) {
    data = this.selectedTickets;
    return data;
  }

}
