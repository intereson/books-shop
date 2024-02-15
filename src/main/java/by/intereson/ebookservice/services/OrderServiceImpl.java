package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateOrderRequest;
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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.intereson.ebookservice.utils.Constants.START_SUM_PRICE;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderListMapper orderListMapper;
    private final ShoppingCartService shoppingCartService;
    private final BookService bookService;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public OrderResponse createOrder(CreateOrderRequest request) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(request.getIdUser());
        if (shoppingCart.getParts().isEmpty()) {
            throw new ShoppingCartIsEmptyException("Shopping cart " + request.getIdUser() + " is empty! Fill the shopping cart and then place an order.");
        } else {
            Order order = new Order();
            order.setOrderStatus(OrderStatus.NEW);
            List<PartOfTheOrder> parts = shoppingCart.getParts();
            Double sum = START_SUM_PRICE;
            for (PartOfTheOrder varPart : parts) {
                sum = varPart.getSumPrice() + sum;
                varPart.setOrder(order);
                varPart.setShoppingCart(null);
                Integer quantity = varPart.getQuantity();
                bookService.reduceFromReserveQuantityBook(varPart.getBook(),quantity );
            }
            order.setSumPrice(sum);
            order.setUser(shoppingCart.getUser());
            shoppingCart.setSumPrice(START_SUM_PRICE);
            shoppingCartService.cleanSumPriceInShoppingCart(request.getIdUser());
            orderRepository.save(order);
            return orderMapper.mapToDTO(order);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id:" + id));
    }

    @Override
    public OrderResponse getOrderByIdDTO(Long id) {
        Order order = getOrderById(id);
        return orderMapper.mapToDTO(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrdersByUserIdDTO(Long userId) {
        List<Order> ordersByUserId = orderRepository.getOrdersByUserId(userId);
        return orderListMapper.mapToDTO(ordersByUserId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return orderListMapper.mapToDTO(orders);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public OrderResponse updateOrderStatusById(Long id, UpdateOrderStatusRequest request) {
        Order order = getOrderById(id);
        order.setOrderStatus(request.getStatus());
        orderRepository.save(order);
        return orderMapper.mapToDTO(order);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
