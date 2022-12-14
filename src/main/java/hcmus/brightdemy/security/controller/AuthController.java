package hcmus.brightdemy.security.controller;

import hcmus.brightdemy.common.ResponseHandler;
import hcmus.brightdemy.constant.ContextPath;
import hcmus.brightdemy.security.dto.LoginDTO;
import hcmus.brightdemy.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(ContextPath.User.LOGIN)
    public Object login(@RequestBody @Valid LoginDTO dto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ResponseHandler.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);
        }
        Object token = authService.login(dto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

}
