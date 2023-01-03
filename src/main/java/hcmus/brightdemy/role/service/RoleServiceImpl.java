package hcmus.brightdemy.role.service;

import hcmus.brightdemy.common.exception.InvalidDataException;
import hcmus.brightdemy.role.dto.CreateRoleDTO;
import hcmus.brightdemy.role.dto.RoleDTO;
import hcmus.brightdemy.role.dto.RoleMapper;
import hcmus.brightdemy.role.entity.Role;
import hcmus.brightdemy.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public RoleDTO findById(int id) {
        Optional<Role> role = roleRepository.findByRoleId(id);
        if (!role.isPresent()) {
            throw new InvalidDataException("Role is not existed. ");
        }
        return RoleMapper.INSTANCE.fromEntityToRoleDTO(role.get());
    }
}
