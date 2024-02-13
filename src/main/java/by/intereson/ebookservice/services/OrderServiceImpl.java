package by.intereson.ebookservice.services;

import by.intereson.ebookservice.dto.requests.CreateOrderRequest;
import by.intereson.ebookservice.dto.response.OrderResponse;
import by.intereson.ebookservice.entities.Order;
import by.intereson.ebookservice.entities.PartOfTheOrder;
import by.intereson.ebookservice.entities.ShoppingCart;
import by.intereson.ebookservice.enums.OrderStatus;
import by.intereson.ebookservice.exceptions.ResourceNotFoundException;
import by.intereson.ebookservice.mappers.OrderListMapper;
import by.intereson.ebookservice.mappers.OrderMapper;
import by.intereson.ebookservice.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderListMapper orderListMapper;
    private final ShoppingCartService shoppingCartService;


    @Override
    @Transactional(readOnly = true)
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id:" + id));
    }

    @Override
    public OrderResponse getOrderByIdDTO(Long id) {
        Order order = getOrderById(id);
        return orderMapper.mapToDTO(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return orderListMapper.mapToDTO(orders);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrdersByUserId(Long userId) {
        List<Order> ordersByUserId = orderRepository.getOrdersByUserId(userId);
        return orderListMapper.mapToDTO(ordersByUserId);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public OrderResponse createOrder(CreateOrderRequest request) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(request.getIdUser());
        Order order = new Order();
        order.setOrderStatus(OrderStatus.NEW);
        List<PartOfTheOrder> parts = shoppingCart.getParts();
        double sum = 0;
        for (PartOfTheOrder varPart : parts) {
            sum = varPart.getSumPrice() + sum;
            varPart.setOrder(order);
            varPart.setShoppingCart(null);
        }
        order.setSumPrice(sum);
        order.setUser(shoppingCart.getUser());
        shoppingCart.setSumPrice(0.0);
        shoppingCartService.cleanSumPriceInShoppingCart(request.getIdUser());
        orderRepository.save(order);
        return orderMapper.mapToDTO(order);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public OrderResponse patchOrderStatus(Long id, OrderStatus status) {
        Order order = getOrderById(id);
        order.setOrderStatus(status);
        orderRepository.save(order);
        return orderMapper.mapToDTO(order);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
