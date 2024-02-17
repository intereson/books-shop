package by.intereson.ebookservice.repositories;

import by.intereson.ebookservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
