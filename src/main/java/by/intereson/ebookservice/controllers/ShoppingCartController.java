package by.intereson.ebookservice.controllers;

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

    /**
     * Get a shopping cart by id
     */
    @GetMapping("shopping-carts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ShoppingCartResponse getShoppingCartByShoppingCartId(@PathVariable Long id) {
        return shoppingCartService.getShoppingCartByShoppingCartIdDto(id);
    }

    /**
     * Clear the shopping cart by id
     * At the same time, parts of the order are deleted from the database
     * and the number of available books and their reserved quantity are recalculated
     */
    @DeleteMapping("shopping-carts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cleanShoppingCartById(@PathVariable Long id) {
        partOfTheOrderService.deletePartsFromShoppingCartByShoppingCartId(id);
    }
}