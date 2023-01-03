package hcmus.brightdemy.user.service;

import hcmus.brightdemy.common.exception.InvalidDataException;
import hcmus.brightdemy.role.entity.Role;
import hcmus.brightdemy.role.repository.RoleRepository;
import hcmus.brightdemy.user.dto.CreateUserDTO;
import hcmus.brightdemy.user.dto.RegisterUserDTO;
import hcmus.brightdemy.user.dto.UserDTO;
import hcmus.brightdemy.user.dto.UserMapper;
import hcmus.brightdemy.user.entity.User;
import hcmus.brightdemy.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDTO create(CreateUserDTO dto) {
        User user = UserMapper.INSTANCE.fromCreateUserDTOToEntity(dto);
        user.setPassword(encoder.encode(dto.getPassword()));

        Optional<Role> roleOpt = roleRepository.findByRoleId(dto.getRoleId());
        if (!roleOpt.isPresent()) {
            throw new InvalidDataException("Role is not existed. ");
        } else {
            user.setRole(roleOpt.get());
        }
        User createUser = userRepository.save(user);
        UserDTO userDTO = UserMapper.INSTANCE.fromEntityToUserDTO(createUser);
        userDTO.setRoleId(createUser.getRole().getRole_id());
        return userDTO;
    }

    @Override
    public List<UserDTO> searchUser(String key, Pageable pageable) {
        List<User> users;
        List<UserDTO> userDTOs = new LinkedList<>();

        if (key != null && pageable == null) {
            users = userRepository.findByUsernameContainingOrFullNameContaining(key, key);
        } else if (key == null && pageable != null) {
            users = userRepository.findAllPaging(pageable);
        } else if (key != null && pageable != null) {
            users = userRepository.findByUsernameContainingOrFullNameContaining(key, key, pageable);
        } else {
            users = userRepository.findAll();
        }

        for (User user : users) {
            UserDTO userDTO = UserMapper.INSTANCE.fromEntityToUserDTO(user);
            userDTO.setRoleId(user.getRole().getRole_id());
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    @Override
    public List<UserDTO> list() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new LinkedList<>();

        for (User user : users) {
            UserDTO userDTO = UserMapper.INSTANCE.fromEntityToUserDTO(user);
            userDTO.setRoleId(user.getRole().getRole_id());
            userDTO.setRoleName(user.getRole().getName());
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    @Override
    public UserDTO register(RegisterUserDTO dto) {
        User user = UserMapper.INSTANCE.fromRegisterUserDTOToEntity(dto);
        user.setPassword(encoder.encode(dto.getPassword()));

        Optional<Role> roleOpt = roleRepository.findByRoleId(0);
        if (!roleOpt.isPresent()) {
            throw new InvalidDataException("Role is not existed. ");
        } else {
            user.setRole(roleOpt.get());
        }
        User createUser = userRepository.save(user);
        UserDTO userDTO = UserMapper.INSTANCE.fromEntityToUserDTO(createUser);
        userDTO.setRoleId(createUser.getRole().getRole_id());
        return userDTO;
    }

    @Override
    public UserDTO getByToken(String token) {
        Optional<User> user = userRepository.findByToken(token);
        if (!user.isPresent()) {
            throw new InvalidDataException("User is not existed. ");
        }
        UserDTO userDTO = UserMapper.INSTANCE.fromEntityToUserDTO(user.get());
        userDTO.setRoleId(user.get().getRole().getRole_id());
        return userDTO;
    }

    @Override
    public void checkRole(String authorization) {
        String[] token = authorization.split(" ");
        Optional<User> user = userRepository.findByToken(token[1]);
        if (!user.isPresent()) {
            throw new InvalidDataException("User is not existed. ");
        }
        if(!user.get().getRole().getName().equals("ADMIN")){
            throw new InvalidDataException("You don't have permission");
        }
    }

    @Override
    public void deleteUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new InvalidDataException("User is not existed. ");
        }
        userRepository.delete(user.get());
    }

    @Override
    public void updateStatusUserById(int id, int status) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new InvalidDataException("User is not existed. ");
        }
        user.get().setStatus(status);
        userRepository.save(user.get());
    }

    @Override
    public UserDTO updateUser(String authorization,RegisterUserDTO userDTO) {
        String token[] = authorization.split(" ");
        Optional<User> user = userRepository.findByToken(token[1]);
        if (!user.isPresent()) {
            throw new InvalidDataException("User is not existed. ");
        }
        if(!userDTO.getFullName().isEmpty()){
            user.get().setFullName(userDTO.getFullName());
        }
        if(!userDTO.getEmail().isEmpty()){
            user.get().setFullName(userDTO.getEmail());
        }
        userRepository.save(user.get());
        return UserMapper.INSTANCE.fromEntityToUserDTO(user.get());
    }
}
