package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateOrderRequest;
import by.intereson.ebookservice.dto.requests.GetOrdersByStatus;
import by.intereson.ebookservice.dto.requests.GetOrdersByStatusAndMoreThenPrice;
import by.intereson.ebookservice.dto.requests.UpdateOrderStatusRequest;
import by.intereson.ebookservice.dto.response.OrderResponse;
import by.intereson.ebookservice.entities.Order;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(CreateOrderRequest request);

    Order getOrder(Long orderId);

    OrderResponse getOrderResponse(Long orderId);

    List<OrderResponse> getOrdersByUserId(Long userId);

    List<OrderResponse> getOrders();

    List<OrderResponse> getOrdersByStatus(GetOrdersByStatus request);

    List<OrderResponse> getOrdersByStatusAndSumMoreThenPrice(GetOrdersByStatusAndMoreThenPrice request);

    OrderResponse updateOrderStatus(Long orderId, UpdateOrderStatusRequest request);

    void deleteOrder(Long orderId);

    void updateOrdersColumnUserId(List<Order> orders);

}
