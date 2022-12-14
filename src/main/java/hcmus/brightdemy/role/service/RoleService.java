package hcmus.brightdemy.role.service;

import hcmus.brightdemy.role.dto.CreateRoleDTO;
import hcmus.brightdemy.role.dto.RoleDTO;

public interface RoleService {
    RoleDTO create(CreateRoleDTO dto);
}
