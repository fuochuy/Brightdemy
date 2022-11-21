package hcmus.brightdemy.service;

import hcmus.brightdemy.entity.User;

import java.util.List;

public interface UserService {
    int createUser(User user);

    User  checkUserExist(String username);

    List<User> getListUser();

    void deleteUser(int id);

    User findById(int id);
}
