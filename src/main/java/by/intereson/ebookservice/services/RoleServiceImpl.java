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
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public RoleResponse createRole(CreateAndUpdateRoleRequest request) {
        Role role = roleMapper.mapToEntity(request);
        roleRepository.save(role);
        return roleMapper.mapToDTO(role);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id:" + id));
    }

    @Override
    public RoleResponse getRoleByIdDTO(Long id) {
        Role role = getRoleById(id);
        return roleMapper.mapToDTO(role);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with name:" + name));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleResponse> getRoles() {
        List<Role> roles = roleRepository.findAll();
        return roleListMapper.toDTOList(roles);
    }


    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public RoleResponse updateRole(Long id, CreateAndUpdateRoleRequest request) {
        Role role = getRoleById(id);
        role.setName(request.getName());
        roleRepository.save(role);
        return roleMapper.mapToDTO(role);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }


}
