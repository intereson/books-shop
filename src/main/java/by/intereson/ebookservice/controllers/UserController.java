package by.intereson.ebookservice.controllers;

import by.intereson.ebookservice.dto.requests.CreateUserRequest;
import by.intereson.ebookservice.dto.requests.UpdateBookLikeRequest;
import by.intereson.ebookservice.dto.response.UserResponse;
import by.intereson.ebookservice.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static by.intereson.ebookservice.utils.Constants.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_URL)
public class UserController {
    private final UserService userService;

    /**
     * Add a new user
     */
    @PostMapping(USERS_URL)
    @ResponseStatus(CREATED)
    public UserResponse createUser(@RequestBody @Valid CreateUserRequest request) {
        return userService.createUser(request);
    }

    /**
     * Get a user by id
     */
    @GetMapping(USERS_ID_URL)
    @ResponseStatus
    public UserResponse getUser(@PathVariable Long id) {
        return userService.getUserResponse(id);
    }

    /**
     * Get all the users
     */
    @GetMapping(USERS_URL)
    @ResponseStatus
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    /**
     * Update user data by user id
     */
    @PutMapping(USERS_ID_URL)
    @ResponseStatus
    public UserResponse updateUser(@PathVariable Long id, @RequestBody @Valid CreateUserRequest request) {
        return userService.updateUser(id, request);
    }

    /**
     * Add/remove a book you like to the list by user id
     */
    @PatchMapping(USERS_ID_URL)
    @ResponseStatus
    public UserResponse updateBookLike(@PathVariable Long id, @RequestBody @Valid UpdateBookLikeRequest request) {
        return userService.updateBookLike(id, request);
    }

    /**
     * Delete a user by user id
     */
    @DeleteMapping(USERS_ID_URL)
    @ResponseStatus(NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
