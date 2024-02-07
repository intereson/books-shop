package by.intereson.ebookservice.services;

import by.intereson.ebookservice.entities.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> getRoles();
    Optional<Role> getRole(String name);
}
