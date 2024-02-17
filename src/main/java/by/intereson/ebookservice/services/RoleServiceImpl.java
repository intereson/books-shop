package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateAndUpdateRoleRequest;
import by.intereson.ebookservice.dto.response.RoleResponse;
import by.intereson.ebookservice.entities.Role;
import by.intereson.ebookservice.exceptions.ResourceNotFoundException;
import by.intereson.ebookservice.mappers.RoleListMapper;
import by.intereson.ebookservice.mappers.RoleMapper;
import by.intereson.ebookservice.repositories.RoleRepository;
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
    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id.toString()));
    }

    @Override
    public RoleResponse getRoleByIdDto(Long id) {
        Role role = getRoleById(id);
        return roleMapper.mapToDto(role);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(name));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleResponse> getRoles() {
        List<Role> roles = roleRepository.findAll();
        return roleListMapper.mapListToDto(roles);
    }


    @Override
    @Transactional(isolation = SERIALIZABLE)
    public RoleResponse updateRoleById(Long id, CreateAndUpdateRoleRequest request) {
        Role role = getRoleById(id);
        role.setName(request.getName());
        roleRepository.save(role);
        return roleMapper.mapToDto(role);
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }

}
