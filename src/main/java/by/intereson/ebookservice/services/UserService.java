package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateUserRequest;
import by.intereson.ebookservice.dto.requests.UpdateBookLikeRequest;
import by.intereson.ebookservice.dto.response.UserResponse;
import by.intereson.ebookservice.entities.User;

import java.util.List;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);

    User getUser(Long userId);

    UserResponse getUserResponse(Long userId);

    List<UserResponse> getUsers();

    UserResponse updateUser(Long userId, CreateUserRequest request);

    UserResponse updateBookLike(Long userId, UpdateBookLikeRequest request);

    void deleteUser(Long userId);
}
