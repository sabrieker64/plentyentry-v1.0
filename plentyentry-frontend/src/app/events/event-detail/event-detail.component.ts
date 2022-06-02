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

  paymentTest(event: EventDTO) {
    // todo make a dropdown to choose payment type before checkout for example paying with paypal or stripe
    // cechkout(card, iban, klarna, etc...)
    // todo nach der logik sollte hier der status auf reserviert gehen vom ticket und von dem jeweiligen user in die
    // shoppingcart hinzugefügt werden
    //this.ticketService.this.eventService.addTicketsToShoppingCart(ticket)
    //this.router.navigateByUrl('/payment/stripe-checkout?eventId=' + event.id);
  }

  addToShoppingCart(eventDTO: EventDTO, quantity: number) {
    //todo einen service machen das die tickets random selektiert und zum warenkorb hinzufügt
    this.eventService.selectTicketsAndAddToCustomerShoppingCart(eventDTO.id, quantity).toPromise().then(data => {
      console.log('successfully added to your shopping cart' + data.toString());
      this.router.navigateByUrl('/shoppingcart/list');
    });
  }

  private ticketFinalize(data: TicketDTO[]) {
    data = this.selectedTickets;
    return data;
  }

  goToCheckout() {

  }
}
