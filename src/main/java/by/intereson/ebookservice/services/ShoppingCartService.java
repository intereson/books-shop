package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.response.ShoppingCartResponse;
import by.intereson.ebookservice.entities.ShoppingCart;

import java.math.BigDecimal;

public interface ShoppingCartService {
    ShoppingCart getShoppingCartById(Long idShoppingCart);

    ShoppingCartResponse getShoppingCartByIdDto(Long idShoppingCart);

    void cleanSumPriceInShoppingCartById(Long idShoppingCart);

    ShoppingCart addInSumPriceInShoppingCartById(Long idShoppingCart, BigDecimal sumPrise);

    void delFromSumPriceInShoppingCartById(Long idShoppingCart, BigDecimal sumPrise);
}
