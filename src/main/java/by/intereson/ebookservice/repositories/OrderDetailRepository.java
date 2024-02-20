package by.intereson.ebookservice.repositories;

import by.intereson.ebookservice.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
