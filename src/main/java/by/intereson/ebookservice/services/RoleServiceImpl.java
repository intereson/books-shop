package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateAndUpdateRoleRequest;
import by.intereson.ebookservice.dto.response.RoleResponse;
import by.intereson.ebookservice.entities.Role;
import by.intereson.ebookservice.exceptions.ResourceNotFoundException;
import by.intereson.ebookservice.mappers.RoleListMapper;
import by.intereson.ebookservice.mappers.RoleMapper;
import by.intereson.ebookservice.repositories.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;
import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final RoleListMapper roleListMapper;

    @Override
    @Transactional(isolation = READ_COMMITTED)
    public RoleResponse createRole(CreateAndUpdateRoleRequest request) {
        Role role = roleMapper.mapToEntity(request);
        roleRepository.save(role);
        return roleMapper.mapToDto(role);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRole(Long roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException(roleId.toString()));
    }

    @Override
    public RoleResponse getRoleResponse(Long roleId) {
        Role role = getRole(roleId);
        return roleMapper.mapToDto(role);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleByRoleName(String roleName) {
        return roleRepository.getRoleByRoleName(roleName)
                .orElseThrow(() -> new EntityNotFoundException(roleName));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleResponse> getRoles() {
        List<Role> roles = roleRepository.findAll();
        return roleListMapper.mapListToDto(roles);
    }


    @Override
    @Transactional(isolation = SERIALIZABLE)
    public RoleResponse updateRole(Long roleId, CreateAndUpdateRoleRequest request) {
        Role role = getRole(roleId);
        role.setRoleName(request.getRoleName());
        roleRepository.save(role);
        return roleMapper.mapToDto(role);
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public void deleteRole(Long roleId) {
        roleRepository.deleteById(roleId);
    }

}
