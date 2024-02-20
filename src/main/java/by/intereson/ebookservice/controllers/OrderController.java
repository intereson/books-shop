package by.intereson.ebookservice.controllers;

import by.intereson.ebookservice.dto.requests.CreateOrderRequest;
import by.intereson.ebookservice.dto.requests.GetOrdersByStatus;
import by.intereson.ebookservice.dto.requests.GetOrdersByStatusAndMoreThenPrice;
import by.intereson.ebookservice.dto.requests.UpdateOrderStatusRequest;
import by.intereson.ebookservice.dto.response.OrderResponse;
import by.intereson.ebookservice.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static by.intereson.ebookservice.utils.Constants.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_URL)
public class OrderController {
    private final OrderService orderService;

    /**
     * Place an order by user id
     * This clears the shopping cart and deletes the reserved books
     */
    @PostMapping(ORDERS_URL)
    @ResponseStatus(CREATED)
    public OrderResponse createOrder(@RequestBody @Valid CreateOrderRequest request) {
        return orderService.createOrder(request);
    }

    /**
     * Get an order by id
     */
    @GetMapping(ORDERS_ID_URL)
    @ResponseStatus
    public OrderResponse getOrder(@PathVariable Long id) {
        return orderService.getOrderResponse(id);
    }

    /**
     * Get all orders by id
     */
    @GetMapping(ORDERS_URL)
    @ResponseStatus
    public List<OrderResponse> getOrders() {
        return orderService.getOrders();
    }

    /**
     * Get all orders by order status
     */
    @GetMapping(ORDERS_STATUS_URL)
    @ResponseStatus
    public List<OrderResponse> getOrdersByStatus(@RequestBody GetOrdersByStatus orderStatus) {
        return orderService.getOrdersByStatus(orderStatus);
    }

    /**
     * Get all orders where order status  and the total cost is more than price
     */
    @GetMapping(ORDERS_SELECTED_URL)
    @ResponseStatus
    public List<OrderResponse> getOrdersByStatusNewAndSumPriceMoreHundred(@RequestBody @Valid GetOrdersByStatusAndMoreThenPrice request) {
        return orderService.getOrdersByStatusAndSumMoreThenPrice(request);
    }

    /**
     * Get all orders placed by the customer by user id
     */
    @GetMapping(USER_ID_ORDERS_URL)
    @ResponseStatus
    public List<OrderResponse> getOrdersByUserId(@PathVariable Long id) {
        return orderService.getOrdersByUserId(id);
    }

    /**
     * The order status is updated by order id
     */
    @PatchMapping(ORDERS_ID_URL)
    @ResponseStatus
    public OrderResponse updateOrderStatus(@PathVariable Long id, @RequestBody @Valid UpdateOrderStatusRequest request) {
        return orderService.updateOrderStatus(id, request);
    }

    /**
     * Deleting an order by order id
     * This removes parts of the order from the database
     */
    @DeleteMapping(ORDERS_ID_URL)
    @ResponseStatus(NO_CONTENT)
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
