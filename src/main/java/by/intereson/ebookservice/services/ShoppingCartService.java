package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.response.ShoppingCartResponse;
import by.intereson.ebookservice.entities.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart getShoppingCart(Long idShoppingCart);

    ShoppingCartResponse getShoppingCartDTO(Long idShoppingCart);

    void cleanSumPriceInShoppingCart(Long idShoppingCart);

    ShoppingCart setSumPriceInShoppingCart(Long idShoppingCart, Double sumPrise);

}
