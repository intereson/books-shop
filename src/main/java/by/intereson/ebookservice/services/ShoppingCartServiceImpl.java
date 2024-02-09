package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.ShoppingCartRequest;
import by.intereson.ebookservice.dto.response.ShoppingCartResponse;
import by.intereson.ebookservice.entities.Book;
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
    private final BookService bookService;
    private final ShoppingCartMapper shoppingCartMapper;

    @Override
    @Transactional
    public ShoppingCartResponse addBookInShoppingCart(Long idShoppingCart, ShoppingCartRequest request) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(idShoppingCart)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id:" + idShoppingCart));
        Book book = bookService.getBook(request.getIdBook());
        shoppingCart.getBooksByShoppingCart().add(book);
        return shoppingCartMapper.mapToDTO(shoppingCart);
    }

    @Override
    public ShoppingCartResponse deleteBookFromShoppingCart(Long idShoppingCart, ShoppingCartRequest request) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(idShoppingCart).orElseThrow(() -> new ResourceNotFoundException("Entity not found with id:" + idShoppingCart));
        Book book = shoppingCart.getBooksByShoppingCart()
                .stream()
                .filter(p -> p.getId().equals(request.getIdBook()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id:" + request.getIdBook()));
        shoppingCart.getBooksByShoppingCart().remove(book);
        return shoppingCartMapper.mapToDTO(shoppingCart);
    }

    @Override
    public ShoppingCartResponse cleanShoppingCart(Long idShoppingCart) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(idShoppingCart).orElseThrow(() -> new ResourceNotFoundException("Entity not found with id:" + idShoppingCart));
        shoppingCart.getBooksByShoppingCart().clear();
        return shoppingCartMapper.mapToDTO(shoppingCart);
    }

    @Override
    public ShoppingCartResponse getShoppingCart(Long idShoppingCart) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(idShoppingCart).orElseThrow(() -> new ResourceNotFoundException("Entity not found with id:" + idShoppingCart));
        return shoppingCartMapper.mapToDTO(shoppingCart);
    }
}
