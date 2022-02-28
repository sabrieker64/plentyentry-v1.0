package at.commodussolutions.plentyentry.user.userdata.rest;

import at.commodussolutions.plentyentry.ordermanagement.ticket.dto.TicketDTO;
import at.commodussolutions.plentyentry.user.userdata.dto.UserAuthReqDTO;
import at.commodussolutions.plentyentry.user.userdata.dto.UserDTO;
import at.commodussolutions.plentyentry.user.userdata.dto.UserLoginDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Eker
 */

@RestController
@RequestMapping(path = "/api/backend/user")
@CrossOrigin
public interface UserRestService {

    //This is for testing, please do not delete. Thanks

    @GetMapping("/{id}")
    @ResponseBody
    UserDTO getUserById(@PathVariable Long id);

    @PostMapping("/register")
    @ResponseBody
    UserDTO createUser(@RequestBody UserDTO userDTO);

    @GetMapping("/confirm")
    @ResponseBody
    String confirm(@RequestParam("token") String token);


    @PostMapping("/authenticate")
    @ResponseBody
    UserDTO createJwtToken(@RequestBody UserAuthReqDTO userAuthReqDTO) throws Exception;

    @GetMapping("/login")
    @ResponseBody
    UserDTO userLogin(@RequestBody UserLoginDTO userLoginDTO) throws Exception;

    //under this comment is everything for the userService please write the rest calls above this comment thanks boys

    @GetMapping("/service/getAge/{id}")
    @ResponseBody
    Integer getUserAge(@PathVariable Long id);

    @GetMapping("/service/getCity/{id}")
    @ResponseBody
    String getUserCity(@PathVariable Long id);

    @GetMapping("/service/getTickets/{id}")
    @ResponseBody
    List<TicketDTO> getUserTickets(@PathVariable Long id);


    @PutMapping
    @ResponseBody
    UserDTO updateUser(UserDTO updatedUser);
}
