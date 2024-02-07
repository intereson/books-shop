package by.intereson.ebookservice.dto.requests;

import by.intereson.ebookservice.entities.Book;
import by.intereson.ebookservice.entities.Order;
import by.intereson.ebookservice.entities.Role;
import by.intereson.ebookservice.entities.ShoppingCart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    private String name;
    private String surname;
    private String email;
    private String login;
    private String password;

}
