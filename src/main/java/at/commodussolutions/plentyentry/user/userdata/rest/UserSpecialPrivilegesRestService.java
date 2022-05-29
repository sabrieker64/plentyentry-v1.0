package at.commodussolutions.plentyentry.user.userdata.rest;

import at.commodussolutions.plentyentry.backendConfig.utils.PESecured;
import at.commodussolutions.plentyentry.user.userdata.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static at.commodussolutions.plentyentry.user.userdata.enums.UserType.ADMIN;
import static at.commodussolutions.plentyentry.user.userdata.enums.UserType.SUPERADMIN;

@RestController
@RequestMapping(path = "/api/backend/user/special-privileges")
@CrossOrigin
public interface UserSpecialPrivilegesRestService {

    @PutMapping()
    @ResponseBody
    @PESecured({ADMIN, SUPERADMIN})
    UserDTO updateUser(@RequestBody UserDTO updatedUser);

    @GetMapping("/{id}")
    @ResponseBody
    @PESecured({ADMIN, SUPERADMIN})
    UserDTO getUserById(@PathVariable Long id);

    @GetMapping("/list")
    @PESecured({ADMIN, SUPERADMIN})
    List<UserDTO> getAllUser();

    @DeleteMapping("/{id}")
    @PESecured({ADMIN, SUPERADMIN})
    void deleteUserById(@PathVariable Long id);
}
