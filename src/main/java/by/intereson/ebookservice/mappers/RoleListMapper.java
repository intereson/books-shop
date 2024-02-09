package by.intereson.ebookservice.mappers;

import by.intereson.ebookservice.dto.response.RoleDTO;
import by.intereson.ebookservice.entities.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface RoleListMapper {
    List<Role> toEntityList(List<RoleDTO> roleDTOList);

    List<RoleDTO> toDTOList(List<Role> roleList);
}
