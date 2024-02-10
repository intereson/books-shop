package by.intereson.ebookservice.dto.response;

import by.intereson.ebookservice.entities.Book;
import by.intereson.ebookservice.entities.Order;
import by.intereson.ebookservice.entities.Role;
import by.intereson.ebookservice.entities.ShoppingCart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String login;
    private String password;
    private LocalDateTime createDateTime;
//    private List<Role> roleList;
//    private ShoppingCart shoppingCart;
//    private List<Order> orders;
//    private List<Book> likedBooks;
}
