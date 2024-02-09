package by.intereson.ebookservice.mappers;

import by.intereson.ebookservice.dto.response.RoleDTO;
import by.intereson.ebookservice.dto.requests.RoleRequest;
import by.intereson.ebookservice.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
    RoleDTO mapToDTO(Role role);

    Role mapToEntity(RoleRequest request);
}
