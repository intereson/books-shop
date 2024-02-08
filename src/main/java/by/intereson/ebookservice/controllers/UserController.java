package by.intereson.ebookservice.controllers;

import by.intereson.ebookservice.dto.UserDTO;
import by.intereson.ebookservice.dto.requests.CreateUserRequest;
import by.intereson.ebookservice.entities.User;
import by.intereson.ebookservice.mappers.UserMapper;
import by.intereson.ebookservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @GetMapping("user/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return UserMapper.INSTANCE.mapToDTO(user);
    }

    @PostMapping("user")
    public UserDTO saveUser(@RequestBody CreateUserRequest request) {
        User user = userService.saveUser(request);
        return UserMapper.INSTANCE.mapToDTO(user);
    }

}
