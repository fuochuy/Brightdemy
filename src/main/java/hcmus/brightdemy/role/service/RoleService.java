package hcmus.brightdemy.role.service;

import hcmus.brightdemy.role.dto.CreateRoleDTO;
import hcmus.brightdemy.role.dto.RoleDTO;
import hcmus.brightdemy.role.entity.Role;

import java.util.List;

public interface RoleService {
    RoleDTO create(CreateRoleDTO dto);
    List<Role> findAll();

    RoleDTO findById(int id);
}
