package hcmus.brightdemy.service.impl;

import hcmus.brightdemy.model.User;
import hcmus.brightdemy.repository.UserRepository;
import hcmus.brightdemy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public int createUser(User user) {
        User newUser = userRepository.save(user);
        return newUser.getId();
    }

    @Override
    public User  checkUserExist(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getListUser() {
        return userRepository.findAll();
    }
}
