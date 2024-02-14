package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateUserRequest;
import by.intereson.ebookservice.dto.requests.UpdateLikedBooksByUserRequest;
import by.intereson.ebookservice.dto.response.UserResponse;
import by.intereson.ebookservice.entities.User;

import java.util.List;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);

    User getUserById(Long id);

    UserResponse getUserByIdDTO(Long id);

    List<UserResponse> getAllUsers();

    UserResponse updateUserByIdDTO(Long id, CreateUserRequest request);

    UserResponse updateLikedBooksByUserIdDTO(Long id, UpdateLikedBooksByUserRequest request);

    void deleteUserById(Long id);
}
