package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.ShoppingCartAddPartRequest;
import by.intereson.ebookservice.dto.response.ShoppingCartResponse;

public interface ShoppingCartService {
    ShoppingCartResponse addPartInShoppingCart(Long idShoppingCart, ShoppingCartAddPartRequest request);

    ShoppingCartResponse deletePartFromShoppingCart(Long idShoppingCart, ShoppingCartAddPartRequest request);

    ShoppingCartResponse cleanShoppingCart(Long idShoppingCart);
    ShoppingCartResponse getShoppingCart(Long idShoppingCart);
}
