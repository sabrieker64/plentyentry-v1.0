package at.commodussolutions.plentyentry.user.userdata.rest;

import at.commodussolutions.plentyentry.user.userdata.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Eker
 */

@RestController
@RequestMapping(path = "/api/backend")
public interface UserRestService {

    //This is for testing, please do not delete. Thanks
    @GetMapping("/hello")
    @ResponseBody
    String getHello();

    @GetMapping("/user/{id}")
    @ResponseBody
    UserDTO getUserById(@PathVariable Long id);

    @PostMapping
    @ResponseBody
    UserDTO createUser(@RequestBody UserDTO userDTO);

}
