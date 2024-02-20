package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.response.ShoppingCartResponse;
import by.intereson.ebookservice.entities.ShoppingCart;
import by.intereson.ebookservice.exceptions.ResourceNotFoundException;
import by.intereson.ebookservice.mappers.ShoppingCartMapper;
import by.intereson.ebookservice.repositories.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static by.intereson.ebookservice.utils.Constants.START_SUM_PRICE;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;


    @Override
    @Transactional(readOnly = true)
    public ShoppingCart getShoppingCart(Long shoppingCartId) {
        return shoppingCartRepository.findById(shoppingCartId)
                .orElseThrow(() -> new ResourceNotFoundException(shoppingCartId));
    }

    @Override
    public void cleanSumPriceInShoppingCart(Long shoppingCartId) {
        ShoppingCart shoppingCart = getShoppingCart(shoppingCartId);
        shoppingCart.setSumPrice(START_SUM_PRICE);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart addSumPriceInShoppingCart(Long shoppingCartId, BigDecimal sumPriseInPart) {
        ShoppingCart shoppingCart = getShoppingCart(shoppingCartId);
        BigDecimal shoppingCartSumPrice = shoppingCart.getSumPrice();
        shoppingCartSumPrice = shoppingCartSumPrice.add(sumPriseInPart);
        shoppingCart.setSumPrice(shoppingCartSumPrice);
        return shoppingCart;
    }

    @Override
    public void delSumPriceFromShoppingCart(Long shoppingCartId, BigDecimal sumPriseInPart) {
        ShoppingCart shoppingCart = getShoppingCart(shoppingCartId);
        BigDecimal shoppingCartSumPrice = shoppingCart.getSumPrice();
        shoppingCartSumPrice = shoppingCartSumPrice.subtract(sumPriseInPart);
        shoppingCart.setSumPrice(shoppingCartSumPrice);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCartResponse getShoppingCartResponse(Long shoppingCartId) {
        return shoppingCartMapper.mapToDto(getShoppingCart(shoppingCartId));
    }
}
