package by.intereson.ebookservice.controllers;

import by.intereson.ebookservice.dto.requests.ShoppingCartRequest;
import by.intereson.ebookservice.dto.response.ShoppingCartResponse;
import by.intereson.ebookservice.services.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user/shopping-cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ShoppingCartResponse getShoppingCart(@PathVariable Long id) {
        return shoppingCartService.getShoppingCart(id);
    }

    @PostMapping("{id}/clean")
    @ResponseStatus(HttpStatus.OK)
    public ShoppingCartResponse cleanShoppingCart(@PathVariable Long id) {
        return shoppingCartService.cleanShoppingCart(id);
    }

    @PostMapping("{id}/add")
    @ResponseStatus(HttpStatus.OK)
    public ShoppingCartResponse addBookInShoppingCart(@PathVariable Long id, @RequestBody ShoppingCartRequest request) {
        return shoppingCartService.addBookInShoppingCart(id, request);
    }

    @PostMapping("{id}/del")
    @ResponseStatus(HttpStatus.OK)
    public ShoppingCartResponse delBookFromShoppingCart(@PathVariable Long id, @RequestBody ShoppingCartRequest request) {
        return shoppingCartService.deleteBookFromShoppingCart(id, request);
    }
}