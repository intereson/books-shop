package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreatePartOfTheOrderRequest;
import by.intereson.ebookservice.dto.requests.UpdatePartOfTheOrderRequest;
import by.intereson.ebookservice.dto.response.PartOfTheOrderResponse;
import by.intereson.ebookservice.entities.Book;
import by.intereson.ebookservice.entities.PartOfTheOrder;
import by.intereson.ebookservice.entities.ShoppingCart;
import by.intereson.ebookservice.exceptions.ResourceNotFoundException;
import by.intereson.ebookservice.mappers.PartOfTheOrderListMapper;
import by.intereson.ebookservice.mappers.PartOfTheOrderMapper;
import by.intereson.ebookservice.repositories.PartOfTheOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
        partOfTheOrder.setBook(book);
        partOfTheOrder.setBookName(book.getBookName());
        Double price = book.getPrice();
        partOfTheOrder.setPrice(price);
        Integer quantity = request.getQuantity();
        Double sumPrise = price * quantity;
        partOfTheOrder.setSumPrice(sumPrise);
        partOfTheOrder.setQuantity(quantity);
        ShoppingCart shoppingCart = shoppingCartService.setSumPriceInShoppingCart(request.getIdShoppingCart(), sumPrise);
        partOfTheOrder.setShoppingCart(shoppingCart);
        partOfTheOrderRepository.save(partOfTheOrder);
        return partOfTheOrderMapper.mapToDTO(partOfTheOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public PartOfTheOrder getPartOfTheOrderById(Long id) {
        return partOfTheOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Part of the order not found with id:" + id));
    }

    @Override
    public PartOfTheOrderResponse getPartOfTheOrderByIdDTO(Long id) {
        return partOfTheOrderMapper.mapToDTO(getPartOfTheOrderById(id));
    }


    @Override
    @Transactional(readOnly = true)
    public List<PartOfTheOrder> getAllPartsOfTheOrder() {
        return partOfTheOrderRepository.findAll();
    }

    @Override
    public List<PartOfTheOrderResponse> getAllPartsOfTheOrderDTO() {
        return partOfTheOrderListMapper.toDTOList(getAllPartsOfTheOrder());
    }


    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public PartOfTheOrderResponse updatePartOfTheOrderById(Long id, UpdatePartOfTheOrderRequest request) {
        Integer newQuantity = request.getQuantity();
        PartOfTheOrder oldPartOfTheOrder = getPartOfTheOrderById(id);
        Double price = oldPartOfTheOrder.getPrice();
        Double sumPrice = price * newQuantity;
        oldPartOfTheOrder.setSumPrice(sumPrice);
        oldPartOfTheOrder.setQuantity(newQuantity);
        partOfTheOrderRepository.save(oldPartOfTheOrder);
        return partOfTheOrderMapper.mapToDTO(oldPartOfTheOrder);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deletePartOfTheOrderById(Long id) {
        partOfTheOrderRepository.deleteById(id);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deleteAllPartsFromShoppingCartByUserId(Long userId) {
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
