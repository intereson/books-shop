package by.intereson.ebookservice.repositories;

import by.intereson.ebookservice.entities.Order;
import by.intereson.ebookservice.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long>,OrderRepositoryCustom {
    List<Order> getOrdersByUserId(Long userId);
    List<Order> getOrdersByOrderStatus(OrderStatus orderStatus);

}
