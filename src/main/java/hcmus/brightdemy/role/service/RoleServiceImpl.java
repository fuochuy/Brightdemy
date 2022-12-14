package hcmus.brightdemy.role.service;

import hcmus.brightdemy.role.dto.CreateRoleDTO;
import hcmus.brightdemy.role.dto.RoleDTO;
import hcmus.brightdemy.role.dto.RoleMapper;
import hcmus.brightdemy.role.entity.Role;
import hcmus.brightdemy.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public RoleDTO create(CreateRoleDTO dto) {
        Role role = RoleMapper.INSTANCE.fromCreateRoleDTOtoEntity(dto);
        Role createRole = roleRepository.save(role);
        return RoleMapper.INSTANCE.fromEntityToRoleDTO(createRole);
    }

    @Override
    public List<RoleDTO> findAll() {
        List<Role> roles = roleRepository.findAll();

        return roles.stream().map(o -> RoleMapper.INSTANCE.fromEntityToRoleDTO(o))
                .collect(Collectors.toList());
    }
}
