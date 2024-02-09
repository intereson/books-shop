package by.intereson.ebookservice.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
