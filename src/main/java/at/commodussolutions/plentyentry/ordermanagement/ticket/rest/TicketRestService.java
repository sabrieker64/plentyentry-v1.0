package at.commodussolutions.plentyentry.ordermanagement.ticket.rest;

import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketDTO;
import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketsToRemove;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/backend/ticket")
@CrossOrigin
public interface TicketRestService {

    @GetMapping("/list")
    @ResponseBody
    List<TicketDTO> getAllTickets();

    @GetMapping("/{id}")
    @ResponseBody
    TicketDTO getTicketById(@PathVariable Long id);

    @GetMapping("/boughtTickets")
    @ResponseBody
    List<TicketDTO> getBoughtTickets();

    //DO WE NEED THIS?
    @PutMapping()
    @ResponseBody
    TicketDTO updateTicketById(@RequestBody TicketDTO updatedTicket);
    //todo nach der bezahlung wenn es funktioniert hat die shoppingcart leeren und die ticketstatuse aktualisieren!!!


    @PutMapping("/update-bought-tickets")
    @ResponseBody
    void updateBoughtTicketStatus(@RequestBody List<TicketDTO> ticketDTOList);

    @PostMapping()
    @ResponseBody
    TicketDTO createNewTicket(@RequestBody TicketDTO ticketDTO);

    @PutMapping("/addToShoppingCart")
    @ResponseBody
    void putTicketsToShoppingCart(@RequestBody List<TicketDTO> ticketDTOSet);

    @GetMapping("/getTicketByEvent/{eventId}/{quantity}")
    @ResponseBody
    List<TicketDTO> findTicketByEvent(@PathVariable Long eventId, @PathVariable Long quantity);

    @GetMapping("/selectTicketsAndAddToCart/{eventId}/{quantity}")
    @ResponseBody
    List<TicketDTO> selectTicketsAndAddToCustomerShoppingCart(@PathVariable Long eventId, @PathVariable Long quantity);


    @PostMapping("/deleteTicketsFromShoppingCart")
    @ResponseBody
    TicketsToRemove deleteTicketsFromShoppingCart(@RequestBody TicketsToRemove tickets);

    @PutMapping("/removeFromShoppingCart")
    @ResponseBody
    List<TicketDTO> removeFromShoppingCart(@RequestParam("ticketId") Long ticketId);

    @GetMapping("/count-ticket-available")
    @ResponseBody
    List<TicketDTO> countTicketThatAreAvailable(@RequestParam("eventId") long eventId);
}
