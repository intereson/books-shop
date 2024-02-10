package by.intereson.ebookservice.mappers;

import by.intereson.ebookservice.dto.response.UserResponse;
import by.intereson.ebookservice.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface UserListMapper {
    List<UserResponse> toDTOList(List<User> users);
}
