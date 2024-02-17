package by.intereson.ebookservice.controllers;

import by.intereson.ebookservice.dto.requests.CreateUserRequest;
import by.intereson.ebookservice.dto.requests.UpdateLikedBooksByUserRequest;
import by.intereson.ebookservice.dto.response.UserResponse;
import by.intereson.ebookservice.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;
    /**
     * Get a user by id
     */
    @GetMapping("users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserByIdDto(id);
    }
    /**
     * Get all the users
     */
    @GetMapping("users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }
    /**
     * Update user data by user id
     */
    @PutMapping("users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUserById(@PathVariable Long id, @RequestBody @Valid CreateUserRequest request) {
        return userService.updateUserByIdDto(id, request);
    }
    /**
     * Add/remove a book you like to the list by user id
     */
    @PatchMapping("users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateLikedBookByUserId(@PathVariable Long id, @RequestBody @Valid UpdateLikedBooksByUserRequest request) {
        return userService.updateLikedBooksByUserIdDto(id, request);
    }
    /**
     * Delete a user by user id
     */
    @DeleteMapping("users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    /**
     * Add a new user
     */
    @PostMapping("users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody @Valid CreateUserRequest request) {
        return userService.createUser(request);
    }

}
