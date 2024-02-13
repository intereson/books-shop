package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.ShoppingCartAddPartRequest;
import by.intereson.ebookservice.dto.requests.ShoppingCartDeletePartRequest;
import by.intereson.ebookservice.dto.response.ShoppingCartResponse;
import by.intereson.ebookservice.entities.PartOfTheOrder;
import by.intereson.ebookservice.entities.ShoppingCart;
import by.intereson.ebookservice.exceptions.ResourceNotFoundException;
import by.intereson.ebookservice.mappers.ShoppingCartMapper;
import by.intereson.ebookservice.repositories.PartOfTheOrderRepository;
import by.intereson.ebookservice.repositories.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;

    private final ShoppingCartMapper shoppingCartMapper;


//    @Override
//    @Transactional
//    public ShoppingCartResponse addPartInShoppingCart(Long idShoppingCart, ShoppingCartAddPartRequest request) {
//        ShoppingCart shoppingCart = getShoppingCart(idShoppingCart);
//        PartOfTheOrder partOfTheOrder = shoppingCart.getParts().stream()
//                .filter((p) -> p.getId().equals(request.getIdPartOfTheOrder())).findFirst()
//                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id:" + request.getIdPartOfTheOrder()));
//        shoppingCart.getParts().add(partOfTheOrder);
//        shoppingCartRepository.save(shoppingCart);
//        return shoppingCartMapper.mapToDTO(shoppingCart);
//    }

//    @Override
//    public ShoppingCartResponse deletePartFromShoppingCart(Long idShoppingCart, ShoppingCartDeletePartRequest request) {
//        ShoppingCart shoppingCart = getShoppingCart(idShoppingCart);
//        PartOfTheOrder partOfTheOrder = shoppingCart.getParts().stream()
//                .filter((p) -> p.getId().equals(request.getIdPartOfTheOrder())).findFirst()
//                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id:" + request.getIdPartOfTheOrder()));
//        shoppingCart.getParts().remove(partOfTheOrder);
//        shoppingCartRepository.save(shoppingCart);
//        return shoppingCartMapper.mapToDTO(shoppingCart);
//    }


    @Override
    public ShoppingCartResponse getShoppingCartDTO(Long idShoppingCart) {
        ShoppingCart shoppingCart = getShoppingCart(idShoppingCart);
        double sum = shoppingCart.getParts().stream().mapToDouble(PartOfTheOrder::getSumPrice).sum();
        shoppingCart.setSumPrice(sum);
        return shoppingCartMapper.mapToDTO(shoppingCart);
    }

    @Override
    public ShoppingCart getShoppingCart(Long idShoppingCart) {
        return shoppingCartRepository.findById(idShoppingCart)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id:" + idShoppingCart));
    }

    @Override
    public void cleanSumPriceInShoppingCart(Long idShoppingCart) {
        getShoppingCart(idShoppingCart).setSumPrice(0.0);
            }
}
