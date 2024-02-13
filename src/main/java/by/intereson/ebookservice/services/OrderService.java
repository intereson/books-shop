package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateOrderRequest;
import by.intereson.ebookservice.dto.response.OrderResponse;
import by.intereson.ebookservice.entities.Order;
import by.intereson.ebookservice.enums.OrderStatus;

import java.util.List;

public interface OrderService {
    Order getOrderById(Long id);
    OrderResponse getOrderByIdDTO(Long id);

    List<OrderResponse> getOrders();

    List<OrderResponse> getOrdersByUserId(Long userId);

    OrderResponse createOrder(CreateOrderRequest request);

    OrderResponse patchOrderStatus(Long id, OrderStatus status);

    void deleteOrder(Long id);
}
