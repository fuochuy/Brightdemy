package hcmus.brightdemy.service.impl;

import hcmus.brightdemy.entity.User;
import hcmus.brightdemy.repository.RoleRepository;
import hcmus.brightdemy.repository.UserRepository;
import hcmus.brightdemy.service.UserService;
import hcmus.brightdemy.utils.ServiceUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service(value = "userService")
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public int createUser(User user) {
        user.setPassword(ServiceUtils.encodePassword(user.getPassword()));
        User newUser = userRepository.save(user);
        return newUser.getId();
    }

    @Override
    public User  checkUserExist(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getListUser() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById((long)id);
    }

    @Override
    public User findById(int id) {
       return userRepository.findById(id);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User [" + username + "] not found");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), getAuthorities(user));
    }

    private Set<SimpleGrantedAuthority> getAuthorities(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
        return authorities;
    }
}
