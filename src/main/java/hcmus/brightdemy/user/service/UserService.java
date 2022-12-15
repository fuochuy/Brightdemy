package hcmus.brightdemy.user.service;

import hcmus.brightdemy.user.dto.CreateUserDTO;
import hcmus.brightdemy.user.dto.UserDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserDTO create(CreateUserDTO dto);

    List<UserDTO> searchUser(String key, Pageable pageable);

    List<UserDTO> list();
}
