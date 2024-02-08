package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.RoleDTO;
import by.intereson.ebookservice.dto.requests.CreateRoleRequest;
import by.intereson.ebookservice.entities.Role;

import java.util.List;

public interface RoleService {
    RoleDTO updateRole(Long id, Role roleDetails);
    void delete(Long id);
    List<RoleDTO> getRoles();
    RoleDTO getRoleById(Long id);
    Role getRoleByName(String name);
    Role saveRole(CreateRoleRequest request);
}
