package by.intereson.ebookservice.mappers;

import by.intereson.ebookservice.dto.requests.RoleRequest;
import by.intereson.ebookservice.dto.response.RoleResponse;
import by.intereson.ebookservice.entities.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {


    RoleResponse mapToDTO(Role role);

    Role mapToEntity(RoleRequest request);
}
