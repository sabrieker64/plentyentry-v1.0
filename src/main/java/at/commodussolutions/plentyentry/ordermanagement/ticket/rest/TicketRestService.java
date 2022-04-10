package at.commodussolutions.plentyentry.ordermanagement.ticket.rest;

import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/backend/ticket")
public interface TicketRestService {

    @GetMapping("/list")
    @ResponseBody
    List<TicketDTO> getAllTickets();

    @GetMapping("/{id}")
    @ResponseBody
    TicketDTO getTicketById(@PathVariable Long id);

    //DO WE NEED THIS?
    @PutMapping()
    @ResponseBody
    TicketDTO updateTicketById(@RequestBody TicketDTO updatedTicket);

    @PostMapping()
    @ResponseBody
    TicketDTO createNewTicket(@RequestBody TicketDTO ticketDTO);

    @PutMapping()
    @ResponseBody
    List<TicketDTO> putTicketsToShoppingCart(@RequestBody Set<TicketDTO> ticketDTOSet);


}
