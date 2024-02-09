package by.intereson.ebookservice.repositories;

import by.intereson.ebookservice.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
}
