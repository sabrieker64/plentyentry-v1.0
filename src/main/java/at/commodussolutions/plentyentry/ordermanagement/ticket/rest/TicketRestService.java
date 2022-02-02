package at.commodussolutions.plentyentry.ordermanagement.ticket.rest;

import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketDTO;
import at.commodussolutions.plentyentry.user.userdata.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/backend/ticket")
public interface TicketRestService {

    @GetMapping("/list")
    @ResponseBody
    List<TicketDTO> getAllTickets();

    @GetMapping("/list")
    @ResponseBody
    List<TicketDTO> getAllTicketsOfUser(@RequestBody UserDTO userDTO);

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


}
