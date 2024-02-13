package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.ShoppingCartAddPartRequest;
import by.intereson.ebookservice.dto.requests.ShoppingCartDeletePartRequest;
import by.intereson.ebookservice.dto.response.ShoppingCartResponse;
import by.intereson.ebookservice.entities.ShoppingCart;

public interface ShoppingCartService {
//    ShoppingCartResponse addPartInShoppingCart(Long idShoppingCart, ShoppingCartAddPartRequest request);
//
//    ShoppingCartResponse deletePartFromShoppingCart(Long idShoppingCart, ShoppingCartDeletePartRequest request);
//
//    ShoppingCartResponse cleanShoppingCart(Long idShoppingCart);
    ShoppingCartResponse getShoppingCartDTO(Long idShoppingCart);
    ShoppingCart getShoppingCart(Long idShoppingCart);
    void cleanSumPriceInShoppingCart(Long idShoppingCart);


}
