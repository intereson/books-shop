package by.intereson.ebookservice.controllers;

import by.intereson.ebookservice.dto.response.ShoppingCartResponse;
import by.intereson.ebookservice.services.OrderDetailService;
import by.intereson.ebookservice.services.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static by.intereson.ebookservice.utils.Constants.API_URL;
import static by.intereson.ebookservice.utils.Constants.SHOPPING_CARTS_ID_URL;
import static org.springframework.http.HttpStatus.NO_CONTENT;


@RestController
@RequiredArgsConstructor
@RequestMapping(API_URL)
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final OrderDetailService orderDetailService;

    /**
     * Get a shopping cart by id
     */
    @GetMapping(SHOPPING_CARTS_ID_URL)
    @ResponseStatus
    public ShoppingCartResponse getShoppingCart(@PathVariable Long id) {
        return shoppingCartService.getShoppingCartResponse(id);
    }

    /**
     * Clear the shopping cart by id
     * At the same time, order details are deleted from the database
     * and the number of available books and their reserved quantity are recalculated
     */
    @DeleteMapping(SHOPPING_CARTS_ID_URL)
    @ResponseStatus(NO_CONTENT)
    public void cleanShoppingCart(@PathVariable Long id) {
        orderDetailService.deleteOrderDetailsFromShoppingCart(id);
    }
}