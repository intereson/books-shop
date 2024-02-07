package by.intereson.ebookservice.mappers;

import by.intereson.ebookservice.dto.responses.CreateUserResponse;
import by.intereson.ebookservice.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperResponse {
    public CreateUserResponse mapToUserResponse(User user){
       return CreateUserResponse.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .build();
    }
}
