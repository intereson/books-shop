package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.response.ShoppingCartResponse;
import by.intereson.ebookservice.entities.ShoppingCart;

import java.math.BigDecimal;

public interface ShoppingCartService {
    ShoppingCart getShoppingCart(Long shoppingCartId);

    ShoppingCartResponse getShoppingCartResponse(Long shoppingCartId);

    void cleanSumPriceInShoppingCart(Long shoppingCartId);

    ShoppingCart addSumPriceInShoppingCart(Long shoppingCartId, BigDecimal sumPrise);

    void delSumPriceFromShoppingCart(Long shoppingCartId, BigDecimal sumPrise);
}
