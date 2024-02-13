package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.response.ShoppingCartResponse;
import by.intereson.ebookservice.entities.PartOfTheOrder;
import by.intereson.ebookservice.entities.ShoppingCart;
import by.intereson.ebookservice.exceptions.ResourceNotFoundException;
import by.intereson.ebookservice.mappers.ShoppingCartMapper;
import by.intereson.ebookservice.repositories.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    @Override
    public ShoppingCart setSumPrice(Long idShoppingCart,Double sumPrise) {
        ShoppingCart shoppingCart = getShoppingCart(idShoppingCart);
        Double shoppingCartSumPrice = shoppingCart.getSumPrice();
        shoppingCartSumPrice = shoppingCartSumPrice + sumPrise;
        shoppingCart.setSumPrice(shoppingCartSumPrice);
        return shoppingCart;
    }

    @Override
    public ShoppingCartResponse getShoppingCartDTO(Long idShoppingCart) {
        ShoppingCart shoppingCart = getShoppingCart(idShoppingCart);
        double sum = shoppingCart.getParts().stream().mapToDouble(PartOfTheOrder::getSumPrice).sum();
        shoppingCart.setSumPrice(sum);
        return shoppingCartMapper.mapToDTO(shoppingCart);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public ShoppingCart getShoppingCart(Long idShoppingCart) {
        return shoppingCartRepository.findById(idShoppingCart)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id:" + idShoppingCart));
    }

    @Override
    public void cleanSumPriceInShoppingCart(Long idShoppingCart) {
        getShoppingCart(idShoppingCart).setSumPrice(0.0);
    }
}
