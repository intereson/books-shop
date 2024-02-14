package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreatePartOfTheOrderRequest;
import by.intereson.ebookservice.dto.requests.UpdatePartOfTheOrderRequest;
import by.intereson.ebookservice.dto.response.PartOfTheOrderResponse;
import by.intereson.ebookservice.entities.PartOfTheOrder;

import java.util.List;


public interface PartOfTheOrderService {
    PartOfTheOrderResponse createPartOfTheOrder(CreatePartOfTheOrderRequest request);

    PartOfTheOrder getPartOfTheOrderById(Long id);

    PartOfTheOrderResponse getPartOfTheOrderByIdDTO(Long id);

    List<PartOfTheOrder> getAllPartsOfTheOrder();

    List<PartOfTheOrderResponse> getAllPartsOfTheOrderDTO();

    PartOfTheOrderResponse updatePartOfTheOrderById(Long id, UpdatePartOfTheOrderRequest request);

    void deletePartOfTheOrderById(Long id);

    void deleteAllPartsFromShoppingCartByUserId(Long userId);
}
