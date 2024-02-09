package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.ShoppingCartRequest;
import by.intereson.ebookservice.dto.response.ShoppingCartResponse;

public interface ShoppingCartService {
    ShoppingCartResponse addBookInShoppingCart(Long idShoppingCart, ShoppingCartRequest request);

    ShoppingCartResponse deleteBookFromShoppingCart(Long idShoppingCart, ShoppingCartRequest request);

    ShoppingCartResponse cleanShoppingCart(Long idShoppingCart);
    ShoppingCartResponse getShoppingCart(Long idShoppingCart);
}
