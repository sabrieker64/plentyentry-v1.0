package at.commodussolutions.plentyentry.user.userdata.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: @Eker
 */

@RestController
@RequestMapping("/api/backend")
public interface UserRestService {

    //This is for testing, please do not delete. Thanks
    @GetMapping("/hello")
    @ResponseBody
    public String getHello();

}
