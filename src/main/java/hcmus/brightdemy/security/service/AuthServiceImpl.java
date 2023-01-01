package hcmus.brightdemy.security.service;


import hcmus.brightdemy.security.dto.CustomUserDetails;
import hcmus.brightdemy.security.dto.LoginDTO;
import hcmus.brightdemy.security.exception.BrightdemyIncorrectLoginException;
import hcmus.brightdemy.security.jwt.JwtUtils;
import hcmus.brightdemy.user.dto.UserMapper;
import hcmus.brightdemy.user.entity.User;
import hcmus.brightdemy.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public Object login(LoginDTO dto) {

        Optional<User> userOpt = userRepository.findByUsername(
                dto.getUsername());

        if (!userOpt.isPresent()) {
            throw new BrightdemyIncorrectLoginException("username or password is not correct");
        }

        String encodedPassword = userOpt.get().getPassword();

        if (!encoder.matches(dto.getPassword(), encodedPassword)) {
            throw new BrightdemyIncorrectLoginException("username or password is not correct");
        }
        if(userOpt.get().getStatus() == 1){
            throw new BrightdemyIncorrectLoginException("Your account has been locked, contact admin for help");
        }

        // 3. create authentication and set into SecurityContext
        CustomUserDetails user = (CustomUserDetails) userDetailsService.loadUserByUsername(dto.getUsername());
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(auth);

        // 4. generate jwt token
        String token = jwtUtils.generateJwtToken(auth);
        userOpt.get().setToken(token);
        userRepository.save(userOpt.get());


        //5. add necessary information into map
        Map<String, String> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("full_name", user.getFullName());
        map.put("email", user.getEmail());
        map.put("role_id", String.valueOf(user.getRoleId()));
        map.put("accessToken", token);

        return map;
    }

}
