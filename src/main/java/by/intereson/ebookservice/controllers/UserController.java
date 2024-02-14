package by.intereson.ebookservice.controllers;

import by.intereson.ebookservice.dto.requests.CreateUserRequest;
import by.intereson.ebookservice.dto.requests.UpdateLikedBooksByUserRequest;
import by.intereson.ebookservice.dto.response.UserResponse;
import by.intereson.ebookservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    @GetMapping("users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUser(@PathVariable Long id) {
        return userService.getUserByIdDTO(id);
    }

    @GetMapping("users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUser(@PathVariable Long id, @RequestBody CreateUserRequest request) {
        return userService.updateUserByIdDTO(id, request);
    }

    @PatchMapping("users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse patchUser(@PathVariable Long id, @RequestBody UpdateLikedBooksByUserRequest request) {
        return userService.updateLikedBooksByUserIdDTO(id, request);
    }

    @DeleteMapping("users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @PostMapping("users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse saveUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }


}
