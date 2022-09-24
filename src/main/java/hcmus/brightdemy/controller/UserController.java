package hcmus.brightdemy.controller;

import hcmus.brightdemy.constant.ContextPath;
import hcmus.brightdemy.constant.ResponseMessage;
import hcmus.brightdemy.model.Response;
import hcmus.brightdemy.model.User;
import hcmus.brightdemy.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(ContextPath.User.PATH)
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = ContextPath.User.CREATE_USER)
    public ResponseEntity<?> createUser(@RequestBody User user) {

        if (userService.checkUserExist(user.getEmail()) != null) {
            Response response = Response.builder()
                    .code(400)
                    .message("email is already in use, please choose another email")
                    .data(null)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        int userId = userService.createUser(user);
        Response response = Response.builder()
                .code(200)
                .message("success")
                .data(userId)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = ContextPath.User.LIST)
    public ResponseEntity<?> getListUser() {
        Response response = Response.builder()
                .code(200)
                .message("success")
                .data(userService.getListUser())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
