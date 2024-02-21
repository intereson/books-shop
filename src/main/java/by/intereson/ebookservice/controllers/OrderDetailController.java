package by.intereson.ebookservice.controllers;

import by.intereson.ebookservice.dto.requests.CreateOrderDetailRequest;
import by.intereson.ebookservice.dto.requests.UpdateOrderDetailRequest;
import by.intereson.ebookservice.dto.response.OrderDetailResponse;
import by.intereson.ebookservice.services.OrderDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static by.intereson.ebookservice.utils.Constants.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
/**
 * The order part class is an object that contains a purchase that consists of a book and a quantity.
 * When creating a request, the shopping cart id, book id and quantity are required.
 * When creating an object, the total cost of the object is calculated and the total cost is added
 * to the shopping cart, also the number of books in the basket fall into the reserved state
 * and are subtracted from the amount available, when deleted, respectively,
 * the decrease in the cost in the shopping cart and from the reserve books fall into the amount available
 * When the basket is cleared, objects are deleted from the database, the quantities in reserve
 * and in stock are recalculated, and when placing an order, they are saved, removed from the reserve
 * and the shopping cart is cleared
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(API_URL)
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    /**
     * Put the book in the shopping cart
     * In this case, you need to transfer the shopping cart id, book id, and the number of books
     */
    @PostMapping(ORDER_DETAILS_URL)
    @ResponseStatus(CREATED)
    public OrderDetailResponse createOrderDetail(@RequestBody @Valid CreateOrderDetailRequest request) {
        return orderDetailService.createOrderDetail(request);
    }

    /**
     * Get order detail by id
     */
    @GetMapping(ORDER_DETAILS_ID_URL)
    @ResponseStatus
    public OrderDetailResponse getOrderDetail(@PathVariable Long id) {
        return orderDetailService.getOrderDetailResponse(id);
    }

    /**
     * Receive all order details
     */
    @GetMapping(ORDER_DETAILS_URL)
    @ResponseStatus
    public List<OrderDetailResponse> getOrderDetails() {
        return orderDetailService.getOrderDetailsResponse();
    }

    /**
     * Update the number of books in the shopping cart by the id of the order detail,
     * at the same time it is necessary to transfer the quantity of at least 1
     */
    @PatchMapping(ORDER_DETAILS_ID_URL)
    @ResponseStatus
    public OrderDetailResponse updateOrderDetail(@PathVariable Long id, @RequestBody @Valid UpdateOrderDetailRequest request) {
        return orderDetailService.updateOrderDetail(id, request);
    }

    /**
     * Delete order detail by id
     */
    @DeleteMapping(ORDER_DETAILS_ID_URL)
    @ResponseStatus(NO_CONTENT)
    public void deleteOrderDetail(@PathVariable Long id) {
        orderDetailService.deleteOrderDetail(id);
    }
}
