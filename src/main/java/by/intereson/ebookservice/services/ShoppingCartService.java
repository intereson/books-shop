package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.response.ShoppingCartResponse;
import by.intereson.ebookservice.entities.ShoppingCart;

import java.math.BigDecimal;

public interface ShoppingCartService {
    ShoppingCart getShoppingCartById(Long idShoppingCart);

    ShoppingCartResponse getShoppingCartByShoppingCartIdDto(Long idShoppingCart);

    void cleanSumPriceInShoppingCartById(Long idShoppingCart);

    ShoppingCart addSumPriceInShoppingCartById(Long idShoppingCart, BigDecimal sumPrise);

    void delSumPriceFromShoppingCartById(Long idShoppingCart, BigDecimal sumPrise);
}
