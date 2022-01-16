package at.commodussolutions.plentyentry.user.userdata.rest;

import at.commodussolutions.plentyentry.user.userdata.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Eker
 */

@RestController
@RequestMapping(path = "/api/backend/user")
public interface UserRestService {

    //This is for testing, please do not delete. Thanks

    @GetMapping("/{id}")
    @ResponseBody
    UserDTO getUserById(@PathVariable Long id);

    @PostMapping
    @ResponseBody
    UserDTO createUser(@RequestBody UserDTO userDTO);

    @GetMapping("/confirm")
    @ResponseBody
    public String confirm(@RequestParam("token") String token);

}
