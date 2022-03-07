package at.commodussolutions.plentyentry.user.userdata.mapper;

import at.commodussolutions.plentyentry.user.userdata.beans.User;
import at.commodussolutions.plentyentry.user.userdata.dto.UserDTO;
import at.commodussolutions.plentyentry.user.userdata.dto.UserRegisterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Author: @Eker
 */

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO mapToDTO(User entity);

    User mapToEntity(UserDTO dto, @MappingTarget User entity);

    User mapToEntityForRegister(UserRegisterDTO userRegisterDTO, @MappingTarget User entity);

    List<UserDTO> mapToListDTO(List<User> user);
}
