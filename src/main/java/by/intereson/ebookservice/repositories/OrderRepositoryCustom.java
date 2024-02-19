package by.intereson.ebookservice.repositories;

import by.intereson.ebookservice.entities.Order;
import by.intereson.ebookservice.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

public interface OrderRepositoryCustom {
    List<Order> findOrdersByStatusAndSumPriceMoreHundred(OrderStatus orderStatus);
}
