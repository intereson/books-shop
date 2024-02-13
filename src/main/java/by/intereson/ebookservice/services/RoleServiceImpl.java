package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateRoleRequest;
import by.intereson.ebookservice.dto.response.RoleResponse;
import by.intereson.ebookservice.entities.Role;
import by.intereson.ebookservice.exceptions.ResourceNotFoundException;
import by.intereson.ebookservice.mappers.RoleListMapper;
import by.intereson.ebookservice.mappers.RoleMapper;
import by.intereson.ebookservice.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final RoleListMapper roleListMapper;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public RoleResponse updateRole(Long id, Role roleDetails) {
        Role updateRole = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found with id:" + id));
        updateRole.setName(roleDetails.getName());
        Role saveRole = roleRepository.save(updateRole);
        return roleMapper.mapToDTO(saveRole);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleResponse> getRoles() {
        List<Role> roles = roleRepository.findAll();
        return roleListMapper.toDTOList(roles);
    }

    @Override
    @Transactional(readOnly = true)
    public RoleResponse getRoleById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found with id:" + id));
        return roleMapper.mapToDTO(role);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name).orElseThrow(() -> new ResourceNotFoundException("Entity not found with name:" + name));
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public RoleResponse saveRole(CreateRoleRequest request) {
        Role role = roleMapper.mapToEntity(request);
        roleRepository.save(role);
        return roleMapper.mapToDTO(role);
    }
}
