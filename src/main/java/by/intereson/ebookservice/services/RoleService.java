package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateAndUpdateRoleRequest;
import by.intereson.ebookservice.dto.response.RoleResponse;
import by.intereson.ebookservice.entities.Role;

import java.util.List;

public interface RoleService {
    RoleResponse createRole(CreateAndUpdateRoleRequest request);

    Role getRole(Long roleId);

    RoleResponse getRoleResponse(Long roleId);

    Role getRoleByRoleName(String roleName);

    List<RoleResponse> getRoles();

    RoleResponse updateRole(Long roleId, CreateAndUpdateRoleRequest request);

    void deleteRole(Long roleId);
}
