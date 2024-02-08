package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateUserRequest;
import by.intereson.ebookservice.entities.User;

public interface UserService {
    User getUser(Long id);
    User saveUser(CreateUserRequest request);
}
