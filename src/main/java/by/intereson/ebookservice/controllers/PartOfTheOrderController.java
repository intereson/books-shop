package by.intereson.ebookservice.controllers;

import by.intereson.ebookservice.dto.requests.CreatePartOfTheOrderRequest;
import by.intereson.ebookservice.dto.requests.UpdatePartOfTheOrderRequest;
import by.intereson.ebookservice.dto.response.PartOfTheOrderResponse;
import by.intereson.ebookservice.services.PartOfTheOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class PartOfTheOrderController {
    private final PartOfTheOrderService partOfTheOrderService;

    /**
     * Get part of the order by id
     */
    @GetMapping("parts-of-the-order/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PartOfTheOrderResponse getPartOfTheOrderById(@PathVariable Long id) {
        return partOfTheOrderService.getPartOfTheOrderByIdDto(id);
    }

    /**
     * Receive all parts of the order
     */
    @GetMapping("parts-of-the-order")
    @ResponseStatus(HttpStatus.OK)
    public List<PartOfTheOrderResponse> getPartsOfTheOrder() {
        return partOfTheOrderService.getPartsOfTheOrderDto();
    }

    /**
     * Put the book in the shopping cart
     * In this case, you need to transfer the shopping cart id, book id, and the number of books
     */
    @PostMapping("parts-of-the-order")
    @ResponseStatus(HttpStatus.CREATED)
    public PartOfTheOrderResponse createPartOfTheOrder(@RequestBody @Valid CreatePartOfTheOrderRequest request) {
        return partOfTheOrderService.createPartOfTheOrder(request);
    }

    /**
     * Update the number of books in the shopping cart by the id of the part of the order,
     * at the same time it is necessary to transfer the quantity of at least 1
     */
    @PatchMapping("parts-of-the-order/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PartOfTheOrderResponse updatePartOfTheOrderById(@PathVariable Long id, @RequestBody @Valid UpdatePartOfTheOrderRequest request) {
        return partOfTheOrderService.updatePartOfTheOrderById(id, request);
    }

    /**
     * Delete part of the order by id
     */
    @DeleteMapping("parts-of-the-order/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePartOfTheOrderById(@PathVariable Long id) {
        partOfTheOrderService.deletePartOfTheOrderById(id);
    }
}
