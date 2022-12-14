package hcmus.brightdemy.role.service;

import hcmus.brightdemy.role.dto.CreateRoleDTO;
import hcmus.brightdemy.role.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    RoleDTO create(CreateRoleDTO dto);
    List<RoleDTO> findAll();
}
