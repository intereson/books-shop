package by.intereson.ebookservice.repositories;

import by.intereson.ebookservice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> getOrdersByUserId(Long userId);
}
