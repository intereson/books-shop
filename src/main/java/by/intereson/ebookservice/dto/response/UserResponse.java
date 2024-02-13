package by.intereson.ebookservice.dto.response;

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
    private List<RoleResponse> roleList;
    private List<OrderResponse> orders;
    private List<BookResponse> likedBooks;
}
