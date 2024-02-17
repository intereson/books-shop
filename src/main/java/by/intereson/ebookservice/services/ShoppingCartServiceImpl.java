package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.response.ShoppingCartResponse;
import by.intereson.ebookservice.entities.PartOfTheOrder;
import by.intereson.ebookservice.entities.ShoppingCart;
import by.intereson.ebookservice.exceptions.ResourceNotFoundException;
import by.intereson.ebookservice.mappers.ShoppingCartMapper;
import by.intereson.ebookservice.repositories.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static by.intereson.ebookservice.utils.Constants.START_SUM_PRICE;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;


    @Override
    @Transactional(readOnly = true)
    public ShoppingCart getShoppingCartById(Long idShoppingCart) {
        return shoppingCartRepository.findById(idShoppingCart)
                .orElseThrow(() -> new ResourceNotFoundException(idShoppingCart.toString()));
    }

    @Override
    public void cleanSumPriceInShoppingCartById(Long idShoppingCart) {
        getShoppingCartById(idShoppingCart).setSumPrice(START_SUM_PRICE);
    }

    @Override
    public ShoppingCart addInSumPriceInShoppingCartById(Long idShoppingCart, BigDecimal sumPrise) {
        ShoppingCart shoppingCart = getShoppingCartById(idShoppingCart);
        BigDecimal shoppingCartSumPrice = shoppingCart.getSumPrice();
        shoppingCartSumPrice = shoppingCartSumPrice.add(sumPrise);
        shoppingCart.setSumPrice(shoppingCartSumPrice);
        return shoppingCart;
    }

    @Override
    public void delFromSumPriceInShoppingCartById(Long idShoppingCart, BigDecimal delsumPrise) {
        ShoppingCart shoppingCart = getShoppingCartById(idShoppingCart);
        BigDecimal shoppingCartSumPrice = shoppingCart.getSumPrice();
        shoppingCartSumPrice = shoppingCartSumPrice.subtract(delsumPrise);
        shoppingCart.setSumPrice(shoppingCartSumPrice);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCartResponse getShoppingCartByIdDto(Long idShoppingCart) {
        ShoppingCart shoppingCart = getShoppingCartById(idShoppingCart);
        List<BigDecimal> decimals = shoppingCart.getParts().stream().map(PartOfTheOrder::getSumPrice).toList();
        BigDecimal sum = START_SUM_PRICE;
        for (BigDecimal bigDecimal : decimals) {
            sum = sum.add(bigDecimal);
        }
        shoppingCart.setSumPrice(sum);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCartMapper.mapToDto(shoppingCart);
    }
}
