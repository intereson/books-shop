package by.intereson.ebookservice.repositories;

import by.intereson.ebookservice.entities.PartOfTheOrder;
import by.intereson.ebookservice.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {

}
