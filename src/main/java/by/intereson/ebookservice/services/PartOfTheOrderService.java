package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreatePartOfTheOrderRequest;
import by.intereson.ebookservice.dto.requests.UpdatePartOfTheOrderRequest;
import by.intereson.ebookservice.dto.response.PartResponse;

import java.util.List;


public interface PartOfTheOrderService {
    PartResponse createPartOfTheOrder(CreatePartOfTheOrderRequest request);
    PartResponse getPartOfTheOrder(Long id);
    List<PartResponse> getAllPartsOfTheOrder();
    PartResponse updatePartOfTheOrder(Long id, UpdatePartOfTheOrderRequest request);
    void deletePartOfTheOrder(Long id);
}
