package hcmus.brightdemy.role.dto;

import hcmus.brightdemy.role.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO fromEntityToRoleDTO (Role role);
    Role fromRoleDTOToEntity (RoleDTO dto);

    Role fromCreateRoleDTOtoEntity (CreateRoleDTO dto);
}
