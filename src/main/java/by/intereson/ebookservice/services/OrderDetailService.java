package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateOrderDetailRequest;
import by.intereson.ebookservice.dto.requests.UpdateOrderDetailRequest;
import by.intereson.ebookservice.dto.response.OrderDetailResponse;
import by.intereson.ebookservice.entities.OrderDetail;

import java.util.List;


public interface OrderDetailService {
    OrderDetailResponse createOrderDetail(CreateOrderDetailRequest request);

    OrderDetail getOrderDetail(Long orderDetailId);

    OrderDetailResponse getOrderDetailResponse(Long orderDetailId);

    List<OrderDetail> getOrderDetails();

    List<OrderDetailResponse> getOrderDetailsResponse();

    OrderDetailResponse updateOrderDetail(Long orderDetailId, UpdateOrderDetailRequest request);

    void deleteOrderDetail(Long orderDetailId);

    void deleteOrderDetailsFromShoppingCart(Long shoppingCartId);

}
