package at.commodussolutions.plentyentry.user.userdata.rest;

import at.commodussolutions.plentyentry.user.userdata.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Eker
 */

@RestController
@RequestMapping("/api/backend")
public interface UserRestService {

    //This is for testing, please do not delete. Thanks
    @GetMapping("/hello")
    @ResponseBody
    String getHello();

    @GetMapping("/user")
    @ResponseBody
    List<UserDTO> getAllUser();
    //End of testing Rest Calls

}
