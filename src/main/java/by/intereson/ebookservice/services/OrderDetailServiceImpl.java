package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateOrderDetailRequest;
import by.intereson.ebookservice.dto.requests.UpdateOrderDetailRequest;
import by.intereson.ebookservice.dto.response.OrderDetailResponse;
import by.intereson.ebookservice.entities.Book;
import by.intereson.ebookservice.entities.OrderDetail;
import by.intereson.ebookservice.entities.ShoppingCart;
import by.intereson.ebookservice.exceptions.QuantityException;
import by.intereson.ebookservice.exceptions.ResourceNotFoundException;
import by.intereson.ebookservice.mappers.OrderDetailListMapper;
import by.intereson.ebookservice.mappers.OrderDetailMapper;
import by.intereson.ebookservice.repositories.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailMapper orderDetailMapper;
    private final OrderDetailListMapper orderDetailListMapper;
    private final BookService bookService;
    private final ShoppingCartService shoppingCartService;


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public OrderDetailResponse createOrderDetail(CreateOrderDetailRequest request) {
        OrderDetail orderDetail = orderDetailMapper.mapToEntity(request);
        Book book = bookService.getBook(request.getBookId());
        if (!isPresentQuantityBook(book, request.getQuantity())) {
            throw new QuantityException(book.getQuantity());
        }
        creatingOrderDetail(request, orderDetail, book);
        orderDetailRepository.save(orderDetail);
        return orderDetailMapper.mapToDto(orderDetail);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDetail getOrderDetail(Long orderDetailId) {
        return orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new ResourceNotFoundException(orderDetailId));
    }

    @Override
    public OrderDetailResponse getOrderDetailResponse(Long orderDetailId) {
        return orderDetailMapper.mapToDto(getOrderDetail(orderDetailId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDetail> getOrderDetails() {
        return orderDetailRepository.findAll();
    }

    @Override
    public List<OrderDetailResponse> getOrderDetailsResponse() {
        return orderDetailListMapper.mapListToDto(getOrderDetails());
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public OrderDetailResponse updateOrderDetail(Long orderDetailId, UpdateOrderDetailRequest request) {
        OrderDetail orderDetail = getOrderDetail(orderDetailId);
        Integer newQuantity = request.getQuantity();
        updatingOrderDetail(request, orderDetail, newQuantity);
        return orderDetailMapper.mapToDto(orderDetail);
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public void deleteOrderDetail(Long orderDetailId) {
        OrderDetail detail = getOrderDetail(orderDetailId);
        Long shoppingCartId = detail.getShoppingCart().getId();
        BigDecimal detailSumPrice = detail.getSumPrice();
        shoppingCartService.delSumPriceFromShoppingCart(shoppingCartId, detailSumPrice);
        updateQuantityBookAndReserveBookWhenDeletingPartOfTheOrder(detail);
        orderDetailRepository.deleteById(orderDetailId);
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public void deleteOrderDetailsFromShoppingCart(Long shoppingCartId) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(shoppingCartId);
        List<OrderDetail> details = shoppingCart.getDetails();
        for (OrderDetail detail : details) {
            updateQuantityBookAndReserveBookWhenDeletingPartOfTheOrder(detail);
            orderDetailRepository.deleteById(detail.getId());
        }
        shoppingCartService.cleanSumPriceInShoppingCart(shoppingCartId);
    }

    private void creatingOrderDetail(CreateOrderDetailRequest request, OrderDetail orderDetail, Book book) {
        bookService.increaseInReserveQuantityBook(book, request.getQuantity());
        bookService.reduceFromQuantityBook(book, request.getQuantity());
        orderDetail.setBook(book);
        orderDetail.setBookName(book.getBookName());
        BigDecimal price = book.getPrice();
        orderDetail.setPrice(price);
        Integer quantity = request.getQuantity();
        BigDecimal sumPrise = price.multiply(BigDecimal.valueOf(quantity));
        orderDetail.setSumPrice(sumPrise);
        orderDetail.setQuantity(quantity);
        ShoppingCart shoppingCart = shoppingCartService.addSumPriceInShoppingCart(request.getShoppingCartId(), sumPrise);
        orderDetail.setShoppingCart(shoppingCart);
    }

    private void updateQuantityBookAndReserveBookWhenDeletingPartOfTheOrder(OrderDetail detail) {
        Integer quantity = detail.getQuantity();
        Book book = detail.getBook();
        bookService.reduceFromReserveQuantityBook(book, quantity);
        bookService.increaseInQuantityBook(book, quantity);
    }

    private boolean isPresentQuantityBook(Book book, Integer quantity) {
        return book.getQuantity() >= quantity;
    }
    private void updatingOrderDetail(UpdateOrderDetailRequest request, OrderDetail orderDetail, Integer newQuantity) {
        int differenceQuantity = request.getQuantity() - orderDetail.getQuantity();
        bookService.reduceFromQuantityBook(orderDetail.getBook(), differenceQuantity);
        bookService.increaseInReserveQuantityBook(orderDetail.getBook(), differenceQuantity);
        BigDecimal price = orderDetail.getPrice();
        BigDecimal sumPrice = price.multiply(BigDecimal.valueOf(newQuantity));
        orderDetail.setSumPrice(sumPrice);
        orderDetail.setQuantity(newQuantity);
    }
}
