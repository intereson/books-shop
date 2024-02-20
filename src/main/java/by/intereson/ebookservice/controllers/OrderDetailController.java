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
