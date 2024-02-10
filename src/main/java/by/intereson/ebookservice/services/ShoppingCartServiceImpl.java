package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.ShoppingCartAddPartRequest;
import by.intereson.ebookservice.dto.response.ShoppingCartResponse;
import by.intereson.ebookservice.entities.ShoppingCart;
import by.intereson.ebookservice.exceptions.ResourceNotFoundException;
import by.intereson.ebookservice.mappers.ShoppingCartMapper;
import by.intereson.ebookservice.repositories.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;

    private final ShoppingCartMapper shoppingCartMapper;

    @Override
    @Transactional
    public ShoppingCartResponse addPartInShoppingCart(Long idShoppingCart, ShoppingCartAddPartRequest request) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(idShoppingCart)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id:" + idShoppingCart));
//        Book book = bookService.getBook(request.getIdBook());
//        shoppingCart.getBooks().add(book);
        return shoppingCartMapper.mapToDTO(shoppingCart);
    }

    @Override
    public ShoppingCartResponse deletePartFromShoppingCart(Long idShoppingCart, ShoppingCartAddPartRequest request) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(idShoppingCart).orElseThrow(() -> new ResourceNotFoundException("Entity not found with id:" + idShoppingCart));

        //        Book book = shoppingCart.getBooks()
//                .stream()
//                .filter(p -> p.getId().equals(request.getIdBook()))
//                .findFirst()
//                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id:" + request.getIdBook()));
//        shoppingCart.getBooks().remove(book);
        return shoppingCartMapper.mapToDTO(shoppingCart);
    }

    @Override
    public ShoppingCartResponse cleanShoppingCart(Long idShoppingCart) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(idShoppingCart).orElseThrow(() -> new ResourceNotFoundException("Entity not found with id:" + idShoppingCart));


        //        shoppingCart.getBooks().clear();
        return shoppingCartMapper.mapToDTO(shoppingCart);
    }

    @Override
    public ShoppingCartResponse getShoppingCart(Long idShoppingCart) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(idShoppingCart).orElseThrow(() -> new ResourceNotFoundException("Entity not found with id:" + idShoppingCart));
        return shoppingCartMapper.mapToDTO(shoppingCart);
    }
}
