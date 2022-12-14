package hcmus.brightdemy.role.controller;

import hcmus.brightdemy.common.ResponseHandler;
import hcmus.brightdemy.constant.ContextPath;
import hcmus.brightdemy.role.dto.CreateRoleDTO;
import hcmus.brightdemy.role.dto.RoleDTO;
import hcmus.brightdemy.role.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping(ContextPath.Role.PATH)
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping(ContextPath.Role.CREATE_ROLE)
    public Object createRole(@Valid @RequestBody CreateRoleDTO dto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ResponseHandler.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);
        }

        RoleDTO createdRole = roleService.create(dto);

        return new ResponseEntity<>(createdRole, HttpStatus.OK);
    }
}
