package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreatePartOfTheOrderRequest;
import by.intereson.ebookservice.dto.requests.UpdatePartOfTheOrderRequest;
import by.intereson.ebookservice.dto.response.PartResponse;
import by.intereson.ebookservice.entities.Book;
import by.intereson.ebookservice.entities.PartOfTheOrder;
import by.intereson.ebookservice.entities.ShoppingCart;
import by.intereson.ebookservice.exceptions.ResourceNotFoundException;
import by.intereson.ebookservice.mappers.PartOfTheOrderListMapper;
import by.intereson.ebookservice.mappers.PartOfTheOrderMapper;
import by.intereson.ebookservice.repositories.PartOfTheOrderRepository;
import by.intereson.ebookservice.repositories.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartOfTheOrderServiceImpl implements PartOfTheOrderService {
    private final PartOfTheOrderMapper partOfTheOrderMapper;
    private final PartOfTheOrderListMapper partOfTheOrderListMapper;
    private final BookService bookService;
    private final ShoppingCartService shoppingCartService;
    private final PartOfTheOrderRepository partOfTheOrderRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public PartResponse createPartOfTheOrder(CreatePartOfTheOrderRequest request) {
        PartOfTheOrder partOfTheOrder = partOfTheOrderMapper.mapToEntity(request);
        Book book = bookService.getBook(request.getIdBook());
        partOfTheOrder.setBook(book);
        partOfTheOrder.setBookName(book.getBookName());
        Double price = book.getPrice();
        partOfTheOrder.setPrice(price);
        Integer quantity = request.getQuantity();
        Double sumPrise = price * quantity;
        partOfTheOrder.setSumPrice(sumPrise);
        partOfTheOrder.setQuantity(quantity);

        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(request.getIdShoppingCart());
        Double shoppingCartSumPrice = shoppingCart.getSumPrice();
        shoppingCartSumPrice = shoppingCartSumPrice + sumPrise;
        shoppingCart.setSumPrice(shoppingCartSumPrice);

        partOfTheOrder.setShoppingCart(shoppingCart);
        partOfTheOrderRepository.save(partOfTheOrder);
        return partOfTheOrderMapper.mapToDTO(partOfTheOrder);
    }

    @Override
    public PartResponse getPartOfTheOrderDTO(Long id) {
        return partOfTheOrderMapper.mapToDTO(getPartOfTheOrder(id));
    }

    @Override
    public PartOfTheOrder getPartOfTheOrder(Long id) {
        return partOfTheOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id:" + id));
    }

    @Override
    public List<PartResponse> getAllPartsOfTheOrderDTO() {
        return partOfTheOrderListMapper.toDTOList(getAllPartsOfTheOrder());
    }

    @Override
    public List<PartOfTheOrder> getAllPartsOfTheOrder() {
        return partOfTheOrderRepository.findAll();
    }

    @Override
    public PartResponse updatePartOfTheOrder(Long id, UpdatePartOfTheOrderRequest request) {
        Integer newQuantity = request.getQuantity();
        PartOfTheOrder oldPartOfTheOrder = getPartOfTheOrder(id);
        Double price = oldPartOfTheOrder.getPrice();
        Double sumPrice = price * newQuantity;
        oldPartOfTheOrder.setSumPrice(sumPrice);
        oldPartOfTheOrder.setQuantity(newQuantity);
        partOfTheOrderRepository.save(oldPartOfTheOrder);
        return partOfTheOrderMapper.mapToDTO(oldPartOfTheOrder);
    }

    @Override
    public void deletePartOfTheOrder(Long id) {
        partOfTheOrderRepository.deleteById(id);
    }

    @Override
    public void deleteAllPartsFromShoppingCart(Long userId) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(userId);
        List<PartOfTheOrder> parts = shoppingCart.getParts();
        List<Long> ids = parts.stream()
                .mapToLong(PartOfTheOrder::getId)
                .boxed()
                .collect(Collectors.toList());
        partOfTheOrderRepository.deleteAllById(ids);
        shoppingCartService.cleanSumPriceInShoppingCart(userId);
    }
}
