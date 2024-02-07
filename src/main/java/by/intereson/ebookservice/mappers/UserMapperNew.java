package by.intereson.ebookservice.mappers;

import by.intereson.ebookservice.dto.UserDTO;
import by.intereson.ebookservice.entities.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapperNew {
    UserDTO mapToDTO(User user);
}
