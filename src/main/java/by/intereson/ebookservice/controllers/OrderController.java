package by.intereson.ebookservice.controllers;

import by.intereson.ebookservice.dto.requests.CreateOrderRequest;
import by.intereson.ebookservice.dto.requests.GetOrdersByOrderStatus;
import by.intereson.ebookservice.dto.requests.UpdateOrderStatusRequest;
import by.intereson.ebookservice.dto.response.OrderResponse;
import by.intereson.ebookservice.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class OrderController {
    private final OrderService orderService;

    /**
     * Get an order by id
     */
    @GetMapping("orders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse getOrderById(@PathVariable Long id) {
        return orderService.getOrderByIdDto(id);
    }
    /**
     * Get all orders by id
     */
    @GetMapping("orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getOrders() {
        return orderService.getOrders();
    }
    /**
     * Get all orders by order status
     */
    @GetMapping("orders/status")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getOrdersByStatus(@RequestBody GetOrdersByOrderStatus orderStatus) {
        return orderService.getOrdersByStatusDto(orderStatus);
    }
    /**
     * Get all orders placed by the customer by user id
     */
    @GetMapping("users/{id}/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getOrdersByUserId(@PathVariable Long id) {
        return orderService.getOrdersByUserIdDto(id);
    }
    /**
     * Place an order by user id
     * This clears the shopping cart and deletes the reserved books
     */
    @PostMapping("orders")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(@RequestBody @Valid CreateOrderRequest request) {
        return orderService.createOrder(request);
    }
    /**
     * The order status is updated by order id
     */
    @PatchMapping("orders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse patchOrderStatus(@PathVariable Long id, @RequestBody @Valid UpdateOrderStatusRequest request) {
        return orderService.updateOrderStatusById(id, request);
    }
    /**
     * Deleting an order by order id
     * This removes parts of the order from the database
     */
    @DeleteMapping("orders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
