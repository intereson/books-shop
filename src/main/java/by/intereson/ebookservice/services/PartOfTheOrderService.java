package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreatePartOfTheOrderRequest;
import by.intereson.ebookservice.dto.requests.UpdatePartOfTheOrderRequest;
import by.intereson.ebookservice.dto.response.PartResponse;
import by.intereson.ebookservice.entities.PartOfTheOrder;

import java.util.List;


public interface PartOfTheOrderService {
    PartResponse createPartOfTheOrder(CreatePartOfTheOrderRequest request);
    PartResponse getPartOfTheOrderDTO(Long id);
    PartOfTheOrder getPartOfTheOrder(Long id);
    List<PartResponse> getAllPartsOfTheOrderDTO();
    List<PartOfTheOrder> getAllPartsOfTheOrder();
    PartResponse updatePartOfTheOrder(Long id, UpdatePartOfTheOrderRequest request);
    void deletePartOfTheOrder(Long id);
    void deleteAllPartsFromShoppingCart(Long userId);
}
