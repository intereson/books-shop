package by.intereson.ebookservice.mappers;

import by.intereson.ebookservice.dto.requests.CreateAndUpdateRoleRequest;
import by.intereson.ebookservice.dto.response.RoleResponse;
import by.intereson.ebookservice.entities.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleResponse mapToDto(Role role);

    Role mapToEntity(CreateAndUpdateRoleRequest request);
}
