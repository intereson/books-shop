package by.intereson.ebookservice.controllers;

import by.intereson.ebookservice.dto.requests.CreateOrderRequest;
import by.intereson.ebookservice.dto.requests.UpdateOrderStatusRequest;
import by.intereson.ebookservice.dto.response.OrderResponse;
import by.intereson.ebookservice.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("orders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse getOrderById(@PathVariable Long id) {
        return orderService.getOrderByIdDTO(id);
    }

    @GetMapping("orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("users/{id}/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getOrdersByUserId(@PathVariable Long id) {
        return orderService.getOrdersByUserIdDTO(id);
    }

    @PostMapping("orders")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(@RequestBody CreateOrderRequest request) {
        return orderService.createOrder(request);
    }

    @PatchMapping("orders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse patchOrderStatus(@PathVariable Long id, @RequestBody UpdateOrderStatusRequest request) {
        return orderService.updateOrderStatusById(id, request);
    }

    @DeleteMapping("orders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
