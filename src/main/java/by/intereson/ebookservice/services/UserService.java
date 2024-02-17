package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateUserRequest;
import by.intereson.ebookservice.dto.requests.UpdateLikedBooksByUserRequest;
import by.intereson.ebookservice.dto.response.UserResponse;
import by.intereson.ebookservice.entities.User;

import java.util.List;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);

    User getUserById(Long id);

    UserResponse getUserByIdDto(Long id);

    List<UserResponse> getUsers();

    UserResponse updateUserByIdDto(Long id, CreateUserRequest request);

    UserResponse updateLikedBooksByUserIdDto(Long id, UpdateLikedBooksByUserRequest request);

    void deleteUserById(Long id);
}
