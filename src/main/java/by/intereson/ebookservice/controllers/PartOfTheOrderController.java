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
    @GetMapping("part-of-the-order/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PartResponse getPartOfTheOrder(@PathVariable Long id) {
        return partOfTheOrderService.getPartOfTheOrder(id);
    }
    @GetMapping("parts-of-the-order")
    @ResponseStatus(HttpStatus.OK)
    public List<PartResponse> getPartsOfTheOrder() {
        return partOfTheOrderService.getAllPartsOfTheOrder();
    }
    @PostMapping("parts-of-the-order")
    @ResponseStatus(HttpStatus.CREATED)
    public PartResponse createPartsOfTheOrder(@RequestBody CreatePartOfTheOrderRequest request) {
        return partOfTheOrderService.createPartOfTheOrder(request);
    }
    @PutMapping("parts-of-the-order/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PartResponse updatePartsOfTheOrder(@PathVariable Long id,@RequestBody UpdatePartOfTheOrderRequest request){
        return partOfTheOrderService.updatePartOfTheOrder(id,request);
    }
    @DeleteMapping("parts-of-the-order/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePartsOfTheOrder(@PathVariable Long id){
        partOfTheOrderService.deletePartOfTheOrder(id);
    }
}
