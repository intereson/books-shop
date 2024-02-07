package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateUserRequest;
import by.intereson.ebookservice.dto.responses.CreateUserResponse;
import by.intereson.ebookservice.entities.User;

public interface UserService {
    CreateUserResponse getUser(Long id);
    CreateUserResponse saveUser(CreateUserRequest request);
}
