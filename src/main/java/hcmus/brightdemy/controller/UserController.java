package hcmus.brightdemy.controller;

import hcmus.brightdemy.constant.ContextPath;
import hcmus.brightdemy.entity.Response;
import hcmus.brightdemy.entity.User;
import hcmus.brightdemy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

        if (userService.checkUserExist(user.getUsername()) != null) {
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

    @PostMapping(value = ContextPath.User.DELETE)
    public ResponseEntity<?> deleteUser(@RequestBody User user) {

        if (userService.findById(user.getId()) != null) {
            userService.deleteUser(user.getId());
            Response response = Response.builder()
                    .code(200)
                    .message("success")
                    .data(user.getId())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        Response response = Response.builder()
                .code(400)
                .message("user not exist")
                .data(user.getId())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
