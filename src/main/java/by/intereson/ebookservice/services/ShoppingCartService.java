package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.response.ShoppingCartResponse;
import by.intereson.ebookservice.entities.ShoppingCart;

public interface ShoppingCartService {

    ShoppingCartResponse getShoppingCartDTO(Long idShoppingCart);

    ShoppingCart getShoppingCart(Long idShoppingCart);

    void cleanSumPriceInShoppingCart(Long idShoppingCart);
    ShoppingCart setSumPrice(Long idShoppingCart,Double sumPrise);

}
