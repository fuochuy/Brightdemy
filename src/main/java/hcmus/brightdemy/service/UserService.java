package hcmus.brightdemy.service;

import hcmus.brightdemy.model.User;

import java.util.List;

public interface UserService {
    int createUser(User user);

    User  checkUserExist(String username);

    List<User> getListUser();
}
