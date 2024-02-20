package by.intereson.ebookservice.repositories;

import by.intereson.ebookservice.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> getRoleByRoleName(String roleName);
}
