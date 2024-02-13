package by.intereson.ebookservice.mappers;

import by.intereson.ebookservice.dto.requests.CreateUserRequest;
import by.intereson.ebookservice.dto.response.UserResponse;
import by.intereson.ebookservice.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BookListMapper.class,
        RoleListMapper.class, OrderListMapper.class})
public interface UserMapper {
    UserResponse mapToDTO(User user);

    User mapToEntity(CreateUserRequest request);
}
