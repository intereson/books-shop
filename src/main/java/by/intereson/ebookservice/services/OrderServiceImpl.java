package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateOrderRequest;
import by.intereson.ebookservice.dto.requests.GetOrdersByOrderStatus;
import by.intereson.ebookservice.dto.requests.UpdateOrderStatusRequest;
import by.intereson.ebookservice.dto.response.OrderResponse;
import by.intereson.ebookservice.entities.Order;
import by.intereson.ebookservice.entities.PartOfTheOrder;
import by.intereson.ebookservice.entities.ShoppingCart;
import by.intereson.ebookservice.enums.OrderStatus;
import by.intereson.ebookservice.exceptions.ResourceNotFoundException;
import by.intereson.ebookservice.exceptions.ShoppingCartIsEmptyException;
import by.intereson.ebookservice.mappers.OrderListMapper;
import by.intereson.ebookservice.mappers.OrderMapper;
import by.intereson.ebookservice.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static by.intereson.ebookservice.utils.Constants.START_SUM_PRICE;
import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderListMapper orderListMapper;
    private final ShoppingCartService shoppingCartService;
    private final BookService bookService;

    @Override
    public List<OrderResponse> getOrdersByStatusDto(GetOrdersByOrderStatus request) {
        List<Order> orders = orderRepository.getOrdersByOrderStatus(request.getOrderStatus());
        return orderListMapper.mapToDto(orders);
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public OrderResponse createOrder(CreateOrderRequest request) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartById(request.getIdUser());
        if (shoppingCart.getParts().isEmpty()) {
            throw new ShoppingCartIsEmptyException(request.getIdUser().toString());
        }
        Order order = new Order();
        List<PartOfTheOrder> parts = shoppingCart.getParts();
        order.setSumPrice(getSumPrice(parts, order));
        order.setOrderStatus(OrderStatus.NEW);
        order.setUser(shoppingCart.getUser());
        shoppingCart.setSumPrice(START_SUM_PRICE);
        shoppingCartService.cleanSumPriceInShoppingCartById(request.getIdUser());
        orderRepository.save(order);
        return orderMapper.mapToDto(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id.toString()));
    }

    @Override
    public OrderResponse getOrderByIdDto(Long id) {
        Order order = getOrderById(id);
        return orderMapper.mapToDto(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrdersByUserIdDto(Long userId) {
        List<Order> ordersByUserId = orderRepository.getOrdersByUserId(userId);
        return orderListMapper.mapToDto(ordersByUserId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return orderListMapper.mapToDto(orders);
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public OrderResponse updateOrderStatusById(Long id, UpdateOrderStatusRequest request) {
        Order order = getOrderById(id);
        order.setOrderStatus(request.getStatus());
        orderRepository.save(order);
        return orderMapper.mapToDto(order);
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void updateOrdersColumnUserId(List<Order> orders) {
        orders.forEach(order -> order.setUser(null));
        orderRepository.saveAll(orders);
    }

    private BigDecimal getSumPrice(List<PartOfTheOrder> parts, Order order) {
        BigDecimal sum = START_SUM_PRICE;
        for (PartOfTheOrder varPart : parts) {
            sum = varPart.getSumPrice().add(sum);
            varPart.setOrder(order);
            varPart.setShoppingCart(null);
            Integer quantity = varPart.getQuantity();
            bookService.reduceFromReserveQuantityBook(varPart.getBook(), quantity);
        }
        return sum;
    }
}
