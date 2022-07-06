package at.commodussolutions.plentyentry.user.shoppingcart.rest.impl;


import at.commodussolutions.plentyentry.ordermanagement.ticket.beans.Ticket;
import at.commodussolutions.plentyentry.ordermanagement.ticket.enums.TicketStatus;
import at.commodussolutions.plentyentry.ordermanagement.ticket.mapper.TicketMapper;
import at.commodussolutions.plentyentry.user.shoppingcart.beans.ShoppingCart;
import at.commodussolutions.plentyentry.user.shoppingcart.dto.ShoppingCartDTO;
import at.commodussolutions.plentyentry.user.shoppingcart.dto.ShoppingCartTicketDTOPerEvent;
import at.commodussolutions.plentyentry.user.shoppingcart.mapper.ShoppingCartMapper;
import at.commodussolutions.plentyentry.user.shoppingcart.rest.ShoppingCartRestService;
import at.commodussolutions.plentyentry.user.shoppingcart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
public class ShoppingCartRestServiceImpl implements ShoppingCartRestService {

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public ShoppingCartDTO getShoppingCartById() {
        var shoppingCart = shoppingCartService.getShoppingCartById();
        var shoppingCartDTO = shoppingCartMapper.mapToDTO(shoppingCartService.getShoppingCartById());
        var everyEvent = shoppingCart.getTickets().stream().map(Ticket::getEvent).collect(Collectors.toList());
        var eventsDistinct = everyEvent.stream().distinct().collect(Collectors.toList());
        var distinctTickets = new ArrayList<ShoppingCartTicketDTOPerEvent>();
        if (shoppingCart.getTickets().stream().filter(ticket -> ticket.getTicketStatus().equals(TicketStatus.NOTSELLED) || ticket.getTicketStatus().equals(TicketStatus.RESERVED)).collect(Collectors.toList())
                .isEmpty()) {
            return shoppingCartDTO;
        }
        if (eventsDistinct.isEmpty()) {
            return shoppingCartDTO;
        }
        eventsDistinct.forEach(event -> {
            var detectedNewEvent =
                    shoppingCart.getTickets().stream().filter(ticket -> ticket.getEvent().getId().equals(event.getId())
                            && ticket.getTicketStatus().equals(TicketStatus.RESERVED)).collect(Collectors.toList());
            var shoppingCartTicketDTOPerEvent = new ShoppingCartTicketDTOPerEvent();
            shoppingCartTicketDTOPerEvent.setTicketDTOS(ticketMapper.mapToListDTO(detectedNewEvent));
            shoppingCartTicketDTOPerEvent.setAmount(getTheRightAmount(event.getPrice(), detectedNewEvent.size()));
            shoppingCartTicketDTOPerEvent.setQuantity(detectedNewEvent.size());
            shoppingCartTicketDTOPerEvent.setTicketDTOS(ticketMapper.mapToListDTO(detectedNewEvent));
            if (detectedNewEvent.get(0) == null) {
                shoppingCartTicketDTOPerEvent.setEventDate(null);
                shoppingCartTicketDTOPerEvent.setEventName(null);
                shoppingCartTicketDTOPerEvent.setEventDescription(null);
                shoppingCartTicketDTOPerEvent.setPricePerTicket(null);
            } else {
                shoppingCartTicketDTOPerEvent.setEventName(detectedNewEvent.get(0).getEvent().getName());
                shoppingCartTicketDTOPerEvent.setEventDescription(detectedNewEvent.get(0).getEvent().getDescription());
                shoppingCartTicketDTOPerEvent.setEventDate(detectedNewEvent.stream().findFirst().orElseThrow().getEvent().getStartDateTime().toLocalDate());
                shoppingCartTicketDTOPerEvent.setPricePerTicket(detectedNewEvent.stream().findFirst().orElseThrow().getEvent().getPrice());
            }
            distinctTickets.add(shoppingCartTicketDTOPerEvent);
        });
        shoppingCartDTO.setTickets(distinctTickets);
        return shoppingCartDTO;
    }

    @Override
    public ShoppingCartDTO updateShoppingCartById(ShoppingCartDTO updatedShoppingCart) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartById();
        return shoppingCartMapper.mapToDTO(shoppingCartService.updateShoppingCartById(shoppingCart));
    }

    private BigDecimal getTheRightAmount(BigDecimal price, int quantity) {
        return price.multiply(BigDecimal.valueOf(quantity)).setScale(2, RoundingMode.HALF_UP);
    }


}
