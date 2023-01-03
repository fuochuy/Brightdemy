package hcmus.brightdemy.user.service;

import hcmus.brightdemy.user.dto.CreateUserDTO;
import hcmus.brightdemy.user.dto.RegisterUserDTO;
import hcmus.brightdemy.user.dto.UserDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserDTO create(CreateUserDTO dto);

    List<UserDTO> searchUser(String key, Pageable pageable);

    List<UserDTO> list();

    UserDTO register(RegisterUserDTO dto);

    UserDTO getByToken(String token);

    void checkRole(String authorization);

    void checkUsernameExists(String username);

    void deleteUserById(int id);

    void updateStatusUserById(int id, int status);

    UserDTO updateUser(String authorization,RegisterUserDTO userDTO);


}
