package hcmus.brightdemy.role.controller;

import hcmus.brightdemy.common.ResponseHandler;
import hcmus.brightdemy.constant.ContextPath;
import hcmus.brightdemy.role.dto.CreateRoleDTO;
import hcmus.brightdemy.role.dto.RoleDTO;
import hcmus.brightdemy.role.entity.Role;
import hcmus.brightdemy.role.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
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
    @GetMapping(ContextPath.Role.LIST)
    public Object getRoles() {
        List<Role> roles = roleService.findAll();
        List<RoleDTO> roleDTOS = new ArrayList<>();
        for(Role role: roles){
            RoleDTO dto= new RoleDTO();
            dto.setCreatedBy(role.getCreatedBy());
            dto.setModifiedBy(role.getModifiedBy());
            dto.setCreatedDate(role.getCreatedDate());
            dto.setModifiedDate(role.getModifiedDate());
            dto.setName(role.getName());
            dto.setDescription(role.getDescription());
            dto.setRoleId(role.getRole_id());
            roleDTOS.add(dto);
        }
        return new ResponseEntity<>(roleDTOS, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public Object findById(@PathVariable int id){
        RoleDTO roleDTO = roleService.findById(id);
        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }

}
