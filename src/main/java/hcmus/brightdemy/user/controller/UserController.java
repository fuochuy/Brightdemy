package hcmus.brightdemy.user.controller;

import hcmus.brightdemy.common.ResponseHandler;
import hcmus.brightdemy.constant.ContextPath;
import hcmus.brightdemy.user.dto.CreateUserDTO;
import hcmus.brightdemy.user.dto.UserDTO;
import hcmus.brightdemy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping(ContextPath.User.CREATE_USER)
    public Object createUser(@RequestHeader String authorization, @Valid @RequestBody CreateUserDTO dto,
                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseHandler.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);
        }

        UserDTO createUser = userService.create(dto);

        return new ResponseEntity<>(createUser, HttpStatus.OK);
    }

    @PostMapping(ContextPath.User.REGISTER)
    public Object register(@Valid @RequestBody CreateUserDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseHandler.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);
        }
        dto.setRoleId(0); //default user

        UserDTO createUser = userService.create(dto);

        return new ResponseEntity<>(createUser, HttpStatus.OK);
    }
    @GetMapping(ContextPath.User.SEARCH)
    public Object searchUser(String key) {
        List<UserDTO> userDTOS = userService.searchUser(key, null);
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }
}
