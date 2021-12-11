package at.commodussolutions.plentyentry.user.userdata.mapper;

import at.commodussolutions.plentyentry.user.userdata.beans.User;
import at.commodussolutions.plentyentry.user.userdata.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Author: @Eker
 */

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO mapToDTO(User entity);

    User mapToEntity(UserDTO dto);

    List<UserDTO> mapToListDTO(List<User> user);

}
