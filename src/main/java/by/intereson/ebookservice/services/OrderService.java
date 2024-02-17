package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateOrderRequest;
import by.intereson.ebookservice.dto.requests.GetOrdersByOrderStatus;
import by.intereson.ebookservice.dto.requests.UpdateOrderStatusRequest;
import by.intereson.ebookservice.dto.response.OrderResponse;
import by.intereson.ebookservice.entities.Order;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(CreateOrderRequest request);

    Order getOrderById(Long id);

    OrderResponse getOrderByIdDto(Long id);

    List<OrderResponse> getOrdersByUserIdDto(Long userId);

    List<OrderResponse> getOrders();

    List<OrderResponse> getOrdersByStatusDto(GetOrdersByOrderStatus request);

    OrderResponse updateOrderStatusById(Long id, UpdateOrderStatusRequest request);

    void deleteOrder(Long id);

    void updateOrdersColumnUserId(List<Order> orders);
}
