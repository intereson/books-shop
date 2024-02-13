package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateOrderRequest;
import by.intereson.ebookservice.dto.response.OrderResponse;
import by.intereson.ebookservice.enums.OrderStatus;

import java.util.List;

public interface OrderService {
    OrderResponse getOrderById(Long id);

    List<OrderResponse> getOrders();

    List<OrderResponse> getOrdersByUserId(Long userId);

    OrderResponse createOrder(CreateOrderRequest request);

    OrderResponse patchOrderStatus(Long id, OrderStatus status);

    void deleteOrder(Long id);
}
