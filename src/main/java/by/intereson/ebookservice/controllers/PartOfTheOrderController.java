package by.intereson.ebookservice.controllers;

import by.intereson.ebookservice.dto.requests.CreatePartOfTheOrderRequest;
import by.intereson.ebookservice.dto.requests.UpdatePartOfTheOrderRequest;
import by.intereson.ebookservice.dto.response.PartResponse;
import by.intereson.ebookservice.services.PartOfTheOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class PartOfTheOrderController {
    private final PartOfTheOrderService partOfTheOrderService;

    @GetMapping("parts-of-the-order/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PartResponse getPartOfTheOrder(@PathVariable Long id) {
        return partOfTheOrderService.getPartOfTheOrderDTO(id);
    }

    @GetMapping("parts-of-the-order")
    @ResponseStatus(HttpStatus.OK)
    public List<PartResponse> getPartsOfTheOrder() {
        return partOfTheOrderService.getAllPartsOfTheOrderDTO();
    }

    @PostMapping("parts-of-the-order")
    @ResponseStatus(HttpStatus.CREATED)
    public PartResponse createPartOfTheOrder(@RequestBody CreatePartOfTheOrderRequest request) {
        return partOfTheOrderService.createPartOfTheOrder(request);
    }

    @PatchMapping("parts-of-the-order/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PartResponse updatePartOfTheOrder(@PathVariable Long id, @RequestBody UpdatePartOfTheOrderRequest request) {
        return partOfTheOrderService.updatePartOfTheOrder(id, request);
    }

    @DeleteMapping("parts-of-the-order/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePartsOfTheOrder(@PathVariable Long id) {
        partOfTheOrderService.deletePartOfTheOrder(id);
    }
}