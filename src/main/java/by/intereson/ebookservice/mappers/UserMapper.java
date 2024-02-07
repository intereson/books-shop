package by.intereson.ebookservice.mappers;

import by.intereson.ebookservice.dto.requests.CreateUserRequest;
import by.intereson.ebookservice.entities.ShoppingCart;
import by.intereson.ebookservice.entities.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserMapper {

    public User mapToUser(CreateUserRequest request) {
        return User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .login(request.getLogin())
                .password(request.getPassword())
                .roleList(new ArrayList<>())
                .shoppingCart(new ShoppingCart())
                .orders(new ArrayList<>())
                .likedBooks(new ArrayList<>())
                .build();
    }


}
