package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateUserRequest;
import by.intereson.ebookservice.dto.requests.UpdateLikedBooksByUserRequest;
import by.intereson.ebookservice.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse getUser(Long id);
    List<UserResponse> getAllUsers();
    UserResponse saveUser(CreateUserRequest request);
    UserResponse updateUser(Long id, CreateUserRequest request);
    UserResponse updateLikedBooksByUser(Long id, UpdateLikedBooksByUserRequest request);
    void deleteUser(Long id);
}
