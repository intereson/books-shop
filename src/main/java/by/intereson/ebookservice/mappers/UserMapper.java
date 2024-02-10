package by.intereson.ebookservice.mappers;

import by.intereson.ebookservice.dto.response.UserResponse;
import by.intereson.ebookservice.dto.requests.CreateUserRequest;
import by.intereson.ebookservice.entities.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {RoleListMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

        @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "email", source = "email")
//        @Mapping(target = "roleList", source = "roleList")
        UserResponse mapToDTO(User user);
    @BeanMapping(ignoreByDefault = true)

    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "login", source = "login")
    @Mapping(target = "password", source = "password")
    User mapToEntity(CreateUserRequest request);
}
