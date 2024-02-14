package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateAndUpdateRoleRequest;
import by.intereson.ebookservice.dto.response.RoleResponse;
import by.intereson.ebookservice.entities.Role;

import java.util.List;

public interface RoleService {
    RoleResponse createRole(CreateAndUpdateRoleRequest request);

    Role getRoleById(Long id);

    RoleResponse getRoleByIdDTO(Long id);

    Role getRoleByName(String name);

    List<RoleResponse> getRoles();


    RoleResponse updateRole(Long id, CreateAndUpdateRoleRequest request);

    void delete(Long id);
}
