package hcmus.brightdemy.security.service;

import hcmus.brightdemy.security.dto.CustomUserDetails;
import hcmus.brightdemy.user.entity.User;
import hcmus.brightdemy.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByUsername(username);

        if(!userOpt.isPresent()) {
            throw new UsernameNotFoundException("username is not existed");
        }

        User currentUser = userOpt.get();

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(currentUser.getRole().getName()));

        CustomUserDetails userDetails = new CustomUserDetails(currentUser.getUsername(), currentUser.getPassword(), authorities);
        userDetails.setEmail(currentUser.getEmail());
        userDetails.setFullName(currentUser.getFullName());
        userDetails.setRoleId(currentUser.getRole().getRole_id());
        return userDetails;
    }
}
