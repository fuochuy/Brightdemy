package hcmus.brightdemy.user.dto;

import hcmus.brightdemy.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO fromEntityToUserDTO (User user);
    User fromUserDTOToEntity (UserDTO dto);

    User fromCreateUserDTOToEntity (CreateUserDTO dto);
    User fromRegisterUserDTOToEntity (RegisterUserDTO dto);
}
