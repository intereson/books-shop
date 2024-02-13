package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.response.RoleResponse;
import by.intereson.ebookservice.dto.requests.CreateRoleRequest;
import by.intereson.ebookservice.entities.Role;

import java.util.List;

public interface RoleService {
    RoleResponse updateRole(Long id, Role roleDetails);
    void delete(Long id);
    List<RoleResponse> getRoles();
    RoleResponse getRoleById(Long id);
    Role getRoleByName(String name);
    RoleResponse saveRole(CreateRoleRequest request);
}
