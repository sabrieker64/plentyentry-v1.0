import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {EventDTO} from "../../definitions/objects";
import {ActivatedRoute, Router} from "@angular/router";
import {EventService} from "../service/event.service";
import {HttpErrorResponse} from "@angular/common/http";
import {ErrorService} from "../../../library/error-handling/error.service";
import {TicketService} from "../../ticket/service/ticket.service";

@Component({
  selector: 'app-event-detail',
  templateUrl: './event-detail.component.html',
  styleUrls: ['./event-detail.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class EventDetailComponent implements OnInit {
  eventDTO: EventDTO = <EventDTO>{};
  eventQuantity: number = 1;

  constructor(private eventService: EventService, private route: ActivatedRoute,
              private errorHandling: ErrorService, private router: Router,
              private ticketService: TicketService) {
  }

  ngOnInit(): void {
    this.getEventDetail();
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

  paymentTest(event: EventDTO) {
    // todo make a dropdown to choose payment type before checkout for example paying with paypal or stripe
    // cechkout(card, iban, klarna, etc...)
    // todo nach der logik sollte hier der status auf reserviert gehen vom ticket und von dem jeweiligen user in die
    // shoppingcart hinzugefügt werden
    //this.ticketService.this.eventService.addTicketsToShoppingCart(ticket)
    this.router.navigateByUrl('/payment/stripe-checkout?eventId=' + event.id);
  }
}
