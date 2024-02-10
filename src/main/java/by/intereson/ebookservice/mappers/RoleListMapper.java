package by.intereson.ebookservice.mappers;

import by.intereson.ebookservice.dto.response.RoleResponse;
import by.intereson.ebookservice.entities.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface RoleListMapper {
       List<RoleResponse> toDTOList(List<Role> roleList);
}
