package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreatePartOfTheOrderRequest;
import by.intereson.ebookservice.dto.requests.UpdatePartOfTheOrderRequest;
import by.intereson.ebookservice.dto.response.PartOfTheOrderResponse;
import by.intereson.ebookservice.entities.Book;
import by.intereson.ebookservice.entities.PartOfTheOrder;
import by.intereson.ebookservice.entities.ShoppingCart;
import by.intereson.ebookservice.exceptions.QuantityException;
import by.intereson.ebookservice.exceptions.ResourceNotFoundException;
import by.intereson.ebookservice.mappers.PartOfTheOrderListMapper;
import by.intereson.ebookservice.mappers.PartOfTheOrderMapper;
import by.intereson.ebookservice.repositories.PartOfTheOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@RequiredArgsConstructor
public class PartOfTheOrderServiceImpl implements PartOfTheOrderService {
    private final PartOfTheOrderRepository partOfTheOrderRepository;
    private final PartOfTheOrderMapper partOfTheOrderMapper;
    private final PartOfTheOrderListMapper partOfTheOrderListMapper;
    private final BookService bookService;
    private final ShoppingCartService shoppingCartService;


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public PartOfTheOrderResponse createPartOfTheOrder(CreatePartOfTheOrderRequest request) {
        PartOfTheOrder partOfTheOrder = partOfTheOrderMapper.mapToEntity(request);
        Book book = bookService.getBookById(request.getIdBook());
        if (!isPresentQuantityBook(book, request.getQuantity())) {
            throw new QuantityException(book.getQuantity().toString());
        }
        bookService.increaseInReserveQuantityBook(book, request.getQuantity());
        bookService.reduceFromQuantityBook(book, request.getQuantity());
        partOfTheOrder.setBook(book);
        partOfTheOrder.setBookName(book.getBookName());
        BigDecimal price = book.getPrice();
        partOfTheOrder.setPrice(price);
        Integer quantity = request.getQuantity();
        BigDecimal sumPrise = price.multiply(BigDecimal.valueOf(quantity));
        partOfTheOrder.setSumPrice(sumPrise);
        partOfTheOrder.setQuantity(quantity);
        ShoppingCart shoppingCart = shoppingCartService.addInSumPriceInShoppingCartById(request.getIdShoppingCart(), sumPrise);
        partOfTheOrder.setShoppingCart(shoppingCart);
        partOfTheOrderRepository.save(partOfTheOrder);
        return partOfTheOrderMapper.mapToDto(partOfTheOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public PartOfTheOrder getPartOfTheOrderById(Long id) {
        return partOfTheOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id.toString()));
    }

    @Override
    public PartOfTheOrderResponse getPartOfTheOrderByIdDto(Long id) {
        return partOfTheOrderMapper.mapToDto(getPartOfTheOrderById(id));
    }


    @Override
    @Transactional(readOnly = true)
    public List<PartOfTheOrder> getPartsOfTheOrder() {
        return partOfTheOrderRepository.findAll();
    }

    @Override
    public List<PartOfTheOrderResponse> getPartsOfTheOrderDto() {
        return partOfTheOrderListMapper.mapListToDto(getPartsOfTheOrder());
    }


    @Override
    @Transactional(isolation = SERIALIZABLE)
    public PartOfTheOrderResponse updatePartOfTheOrderById(Long id, UpdatePartOfTheOrderRequest request) {
        Integer newQuantity = request.getQuantity();
        PartOfTheOrder oldPartOfTheOrder = getPartOfTheOrderById(id);
        int differenceQuantity = request.getQuantity() - oldPartOfTheOrder.getQuantity();
        bookService.reduceFromQuantityBook(oldPartOfTheOrder.getBook(), differenceQuantity);
        bookService.increaseInReserveQuantityBook(oldPartOfTheOrder.getBook(), differenceQuantity);
        BigDecimal price = oldPartOfTheOrder.getPrice();
        BigDecimal sumPrice = price.multiply(BigDecimal.valueOf(newQuantity));
        oldPartOfTheOrder.setSumPrice(sumPrice);
        oldPartOfTheOrder.setQuantity(newQuantity);
        partOfTheOrderRepository.save(oldPartOfTheOrder);
        return partOfTheOrderMapper.mapToDto(oldPartOfTheOrder);
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public void deletePartOfTheOrderById(Long id) {
        PartOfTheOrder part = getPartOfTheOrderById(id);
        Long idShoppingCart = part.getShoppingCart().getIdShoppingCart();
        BigDecimal partSumPrice = part.getSumPrice();
        shoppingCartService.delFromSumPriceInShoppingCartById(idShoppingCart, partSumPrice);
        Integer quantity = part.getQuantity();
        Book book = part.getBook();
        bookService.reduceFromReserveQuantityBook(book, quantity);
        bookService.increaseInQuantityBook(book, quantity);
        partOfTheOrderRepository.deleteById(id);
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public void deletePartsFromShoppingCartById(Long id) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartById(id);
        List<PartOfTheOrder> parts = shoppingCart.getParts();
        for (PartOfTheOrder part : parts) {
            deletePartOfTheOrderById(part.getId());
        }
        shoppingCartService.cleanSumPriceInShoppingCartById(id);
    }

    @Override
    public boolean isPresentQuantityBook(Book book, Integer quantity) {
        return book.getQuantity() >= quantity;
    }
}
