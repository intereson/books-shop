package by.intereson.ebookservice.controllers;

import by.intereson.ebookservice.dto.requests.ShoppingCartAddPartRequest;
import by.intereson.ebookservice.dto.requests.ShoppingCartDeletePartRequest;
import by.intereson.ebookservice.dto.response.ShoppingCartResponse;
import by.intereson.ebookservice.services.PartOfTheOrderService;
import by.intereson.ebookservice.services.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final PartOfTheOrderService partOfTheOrderService;
    @GetMapping("users/{id}/shopping-cart")
    @ResponseStatus(HttpStatus.OK)
    public ShoppingCartResponse getShoppingCart(@PathVariable Long id) {
        return shoppingCartService.getShoppingCartDTO(id);
    }
//    @PutMapping("shopping-carts/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public ShoppingCartResponse cleanShoppingCart(@PathVariable Long id) {
//        return shoppingCartService.cleanShoppingCart(id);
//    }
    @DeleteMapping("users/{id}/shopping-cart")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cleanShoppingCart(@PathVariable Long id){
        partOfTheOrderService.deleteAllPartsFromShoppingCart(id);
    }
}