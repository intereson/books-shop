package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateOrderRequest;
import by.intereson.ebookservice.dto.requests.GetOrdersByStatus;
import by.intereson.ebookservice.dto.requests.GetOrdersByStatusAndMoreThenPrice;
import by.intereson.ebookservice.dto.requests.UpdateOrderStatusRequest;
import by.intereson.ebookservice.dto.response.OrderResponse;
import by.intereson.ebookservice.entities.Order;
import by.intereson.ebookservice.entities.OrderDetail;
import by.intereson.ebookservice.entities.ShoppingCart;
import by.intereson.ebookservice.enums.OrderStatus;
import by.intereson.ebookservice.exceptions.ResourceNotFoundException;
import by.intereson.ebookservice.exceptions.ShoppingCartIsEmptyException;
import by.intereson.ebookservice.mappers.OrderListMapper;
import by.intereson.ebookservice.mappers.OrderMapper;
import by.intereson.ebookservice.repositories.order.OrderRepository;
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
    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(request.getUserId());
        if (shoppingCart.getDetails().isEmpty()) {
            throw new ShoppingCartIsEmptyException(request.getUserId());
        }
        Order order = creatingOrder(request, shoppingCart);
        orderRepository.save(order);
        return orderMapper.mapToDto(order);
    }

    @Override
    public List<OrderResponse> getOrdersByStatus(GetOrdersByStatus request) {
        List<Order> orders = orderRepository.getOrdersByOrderStatus(request.getOrderStatus());
        return orderListMapper.mapToDto(orders);
    }

    @Override
    public List<OrderResponse> getOrdersByStatusAndSumMoreThenPrice(GetOrdersByStatusAndMoreThenPrice request) {
        List<Order> orders = orderRepository
                .findOrdersByStatusAndSumPriceMoreRequestPrice(request.getOrderStatus(), request.getPrice());
        return orderListMapper.mapToDto(orders);
    }

    @Override
    @Transactional(readOnly = true)
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException(orderId));
    }

    @Override
    public OrderResponse getOrderResponse(Long orderId) {
        Order order = getOrder(orderId);
        return orderMapper.mapToDto(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrdersByUserId(Long userId) {
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
    public OrderResponse updateOrderStatus(Long orderId, UpdateOrderStatusRequest request) {
        Order order = getOrder(orderId);
        order.setOrderStatus(request.getStatus());
        return orderMapper.mapToDto(order);
    }

    @Override
    @Transactional(isolation = SERIALIZABLE)
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public void updateOrdersColumnUserId(List<Order> orders) {
        orders.forEach(order -> order.setUser(null));
    }

    private BigDecimal getSumPrice(List<OrderDetail> parts, Order order) {
        BigDecimal sum = START_SUM_PRICE;
        for (OrderDetail varPart : parts) {
            sum = varPart.getSumPrice().add(sum);
            varPart.setOrder(order);
            varPart.setShoppingCart(null);
            Integer quantity = varPart.getQuantity();
            bookService.reduceFromReserveQuantityBook(varPart.getBook(), quantity);
        }
        return sum;
    }

    private Order creatingOrder(CreateOrderRequest request, ShoppingCart shoppingCart) {
        Order order = new Order();
        List<OrderDetail> details = shoppingCart.getDetails();
        order.setSumPrice(getSumPrice(details, order));
        order.setOrderStatus(OrderStatus.NEW);
        order.setUser(shoppingCart.getUser());
        shoppingCart.setSumPrice(START_SUM_PRICE);
        shoppingCartService.cleanSumPriceInShoppingCart(request.getUserId());
        return order;
    }
}
