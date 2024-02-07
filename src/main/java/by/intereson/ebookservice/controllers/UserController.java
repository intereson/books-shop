package by.intereson.ebookservice.controllers;

import by.intereson.ebookservice.dto.requests.CreateUserRequest;
import by.intereson.ebookservice.dto.responses.CreateUserResponse;
import by.intereson.ebookservice.entities.User;
import by.intereson.ebookservice.mappers.UserMapper;
import by.intereson.ebookservice.mappers.UserMapperNew;
import by.intereson.ebookservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;
    private final UserMapperNew userMapperNew;
    @GetMapping("user/{id}")
    public CreateUserResponse getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping("user")
    public CreateUserResponse saveUser(@RequestBody CreateUserRequest request) {
        return userService.saveUser(request);
    }

}
