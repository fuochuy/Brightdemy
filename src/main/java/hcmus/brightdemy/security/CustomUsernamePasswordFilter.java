package hcmus.brightdemy.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import hcmus.brightdemy.constant.ResponseMessage;
import hcmus.brightdemy.model.User;
import hcmus.brightdemy.repository.UserRepository;
import hcmus.brightdemy.utils.ResponseUtils;
import hcmus.brightdemy.utils.ServiceUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CustomUsernamePasswordFilter extends UsernamePasswordAuthenticationFilter {
    private String userName = null;

    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenProvider jwtTokenProvider;

    @Bean
    public FilterRegistrationBean registration(HiddenHttpMethodFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        String input = null;

        try {
            input = ServiceUtils.getStringStream(request.getInputStream(), request.getContentLength());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Map authenData = new HashMap();

        try {
            authenData = objectMapper.readValue(input, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String username = authenData.get("username").toString();
        String password = authenData.get("password").toString();
        userName = username;

        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        User user = repository.findByUsername(userName);
        if (user.getStatus() >= 3) {
            responseToClient(new JSONObject(ResponseUtils.buildResponse(ResponseMessage.USER_HAS_BEEN_LOCKED)).toString(), response);
        } else {
            user.setStatus(0);
            repository.save(user);
        }
        String token = jwtTokenProvider.generateToken(authResult);

        responseToClient(new JSONObject(ResponseUtils.buildResponse(ResponseMessage.SUCCESSFUL, token)).toString(), response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        User user = repository.findByUsername(userName);
        if (user != null) {
            if (repository.findById(user.getId()).getRole().equals("USER")) {
                user.setStatus(user.getStatus() + 1);
            }
            repository.save(user);
        }
        responseToClient(new JSONObject(ResponseUtils.buildResponse(ResponseMessage.LOGIN_FAIL)).toString(), response);
    }

    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    private void responseToClient(String message, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getOutputStream().write(message.getBytes(StandardCharsets.UTF_8));
        response.getOutputStream().close();
    }
}
